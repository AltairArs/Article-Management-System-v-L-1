package com.authenticationservice.services;

import com.authenticationservice.domain.dto.responses.JwtAuthenticationResponse;
import com.authenticationservice.domain.models.entities.UserEntity;

public interface AuthenticationService {
    public abstract JwtAuthenticationResponse register(String email, String password);
    public abstract JwtAuthenticationResponse authenticate(String email, String password);
    public abstract JwtAuthenticationResponse refreshToken(String refreshToken);
    public abstract void logout(UserEntity user);
}
