package com.authenticationservice.services;

import com.authenticationservice.domain.dto.responses.JwtAuthenticationResponse;
import com.authenticationservice.domain.models.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public abstract String extractFromToken(String jwtToken, String fieldName);
    public abstract String generateAccessToken(UserDetails userDetails);
    public abstract String generateRefreshToken(UserDetails userDetails);
    public abstract JwtAuthenticationResponse generateTokenResponse(UserEntity user);
}
