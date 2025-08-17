package com.fernandes.gamermanager.java.service;

import com.fernandes.gamermanager.java.documents.UserDocument;
import org.springframework.security.crypto.password.PasswordEncoder;


public class AuthService {

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public boolean validatePassword(String requestPassword, String userPassword) {
        return passwordEncoder.matches(requestPassword, userPassword);
    }

    public String generateToken(UserDocument user) {
        return tokenService.generateToken(user);
    }
}
