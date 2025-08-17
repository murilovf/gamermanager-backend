package com.fernandes.gamermanager.java.service;

import com.fernandes.gamermanager.java.documents.UserDocument;
import com.fernandes.gamermanager.java.exceptions.UserAlreadyExistsException;
import com.fernandes.gamermanager.java.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDocument save(UserDocument userDocument) {
        validateEmail(userDocument.getEmail());
        userDocument.setPassword(passwordEncoder.encode(userDocument.getPassword()));
        return userRepository.save(userDocument);
    }

    private void validateEmail(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Já existe um usuário com esse email cadastrado");
        }
    }
}
