package com.authenticationservice.services.impl;

import com.authenticationservice.domain.dto.responses.JwtAuthenticationResponse;
import com.authenticationservice.domain.models.entities.UserEntity;
import com.authenticationservice.domain.models.redis.JwtTokenRedisHash;
import com.authenticationservice.exceptions.EntityAlreadyExistsException;
import com.authenticationservice.exceptions.EntityNotFoundException;
import com.authenticationservice.exceptions.InvalidDataException;
import com.authenticationservice.repo.entity.UserRepository;
import com.authenticationservice.repo.redis.JwtTokenRepository;
import com.authenticationservice.services.AuthenticationService;
import com.authenticationservice.services.JwtService;
import com.authenticationservice.services.PasswordEncoderService;
import com.authenticationservice.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final JwtService jwtService;
    private final UserService userService;
    private final JwtTokenRepository jwtTokenRepository;

    @Override
    public JwtAuthenticationResponse register(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new EntityAlreadyExistsException("User with this email already exists: " + email);
        }
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(passwordEncoderService.encode(password))
                .build();
        userRepository.save(user);
        return jwtService.generateTokenResponse(user);
    }

    @Override
    public JwtAuthenticationResponse authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email,
                password
        ));
        UserEntity user = userService.getUserByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return jwtService.generateTokenResponse(user);
    }

    @Override
    public JwtAuthenticationResponse refreshToken(String refreshToken) {
        JwtTokenRedisHash tokenRedisHash = jwtTokenRepository.getByToken(refreshToken)
                .orElseThrow(() -> new EntityNotFoundException("Token is invalid or expired"));
        if (!tokenRedisHash.getTokenType().equals("REFRESH"))
            throw new InvalidDataException("Invalid token type, expected REFRESH, found: " + tokenRedisHash.getTokenType());
        String email = jwtService.extractFromToken(tokenRedisHash.getToken(), "email");
        if (email == null)
            throw new InvalidDataException("Provided token is invalid");
        jwtTokenRepository.deleteAll(jwtTokenRepository.findAllByUserEmail(email));
        return jwtService.generateTokenResponse(userService.getUserByEmail(email));
    }

    @Override
    public void logout(UserEntity user) {
        jwtTokenRepository.deleteAll(jwtTokenRepository.findAllByUserEmail(user.getEmail()));
    }
}
