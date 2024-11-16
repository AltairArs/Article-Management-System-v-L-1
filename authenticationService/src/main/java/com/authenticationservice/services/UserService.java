package com.authenticationservice.services;

import com.authenticationservice.domain.models.entities.UserEntity;

public interface UserService {
    public abstract UserEntity getUserByEmail(String email);
    public abstract UserEntity updateUser(UserEntity user, String new_password);
}
