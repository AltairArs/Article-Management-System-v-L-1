package com.authenticationservice.services.impl;

import com.authenticationservice.domain.models.entities.UserEntity;
import com.authenticationservice.exceptions.EntityNotFoundException;
import com.authenticationservice.repo.entity.UserRepository;
import com.authenticationservice.services.PasswordEncoderService;
import com.authenticationservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.getByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    @Override
    public UserEntity updateUser(UserEntity user, String new_password) {
        user.setPassword(passwordEncoderService.encode(new_password));
        return userRepository.save(user);
    }
}
