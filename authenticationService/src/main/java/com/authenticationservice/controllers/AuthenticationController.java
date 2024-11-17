package com.authenticationservice.controllers;

import com.authenticationservice.domain.dto.responses.JwtAuthenticationResponse;
import com.authenticationservice.domain.models.entities.UserEntity;
import com.authenticationservice.domain.models.redis.JwtTokenRedisHash;
import com.authenticationservice.repo.redis.JwtTokenRepository;
import com.authenticationservice.services.AuthenticationService;
import com.authenticationservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @QueryMapping
    public UserEntity currentUser(@AuthenticationPrincipal UserEntity user) {
        return user;
    }

    @MutationMapping
    public JwtAuthenticationResponse authenticate(@Argument final String email, @Argument final String password) {
        return authenticationService.authenticate(email, password);
    }

    @MutationMapping
    public JwtAuthenticationResponse register(@Argument final String email, @Argument final String password) {
        return authenticationService.register(email, password);
    }

    @MutationMapping
    public JwtAuthenticationResponse refreshToken(@Argument final String refreshToken) {
        return authenticationService.refreshToken(refreshToken);
    }

    @MutationMapping
    public void logout(@AuthenticationPrincipal UserEntity user) {
        authenticationService.logout(user);
        SecurityContextHolder.clearContext();
    }

    @MutationMapping
    public UserEntity updateUser(@AuthenticationPrincipal UserEntity user, @Argument final String password) {
        return userService.updateUser(user, password);
    }
}
