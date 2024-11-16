package com.authenticationservice.services;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface PasswordEncoderService {
    public abstract String encode(String password);
    public abstract PasswordEncoder getPasswordEncoder();
}
