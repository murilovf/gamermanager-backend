package com.fernandes.gamermanager.java.config;

import com.fernandes.gamermanager.java.repositories.UserRepository;
import com.fernandes.gamermanager.java.service.AuthService;
import com.fernandes.gamermanager.java.service.LoginService;
import com.fernandes.gamermanager.java.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationConfig {

    @Bean
    AuthService authService(PasswordEncoder passwordEncoder, TokenService tokenService) {
        return new AuthService(passwordEncoder, tokenService);
    }

    @Bean
    LoginService loginService(AuthService authService, UserRepository userRepository) {
        return new LoginService(authService, userRepository);
    }
 }
