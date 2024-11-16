package com.authenticationservice.services.impl;

import com.authenticationservice.services.PasswordEncoderService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceEncoderServiceImpl implements PasswordEncoderService {
    @Override
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

    @Override
    public String encode(String password) {
        return getPasswordEncoder().encode(password);
    }
}
