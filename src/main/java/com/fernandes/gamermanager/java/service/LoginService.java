package com.fernandes.gamermanager.java.service;

import com.fernandes.gamermanager.java.documents.UserDocument;
import com.fernandes.gamermanager.java.exceptions.UnauthorizedException;
import com.fernandes.gamermanager.java.repositories.UserRepository;


public class LoginService {

    private final UserRepository userRepository;

    private final AuthService authService;

    public LoginService(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }


    public String login(String email, String password) {
        UserDocument user = userRepository.findUserByEmail(email).orElseThrow(() -> new UnauthorizedException("Usuário não encontrado"));
        if (authService.validatePassword(password, user.getPassword())) {
            return authService.generateToken(user);
        }

        throw new UnauthorizedException("Erro ao autenticar");
    }
}
