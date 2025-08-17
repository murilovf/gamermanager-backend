package com.fernandes.gamermanager.java.controller;

import com.fernandes.gamermanager.java.dto.LoginRequest;
import com.fernandes.gamermanager.java.dto.LoginResponse;
import com.fernandes.gamermanager.java.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest body) {
        String token = loginService.login(body.email(), body.password());
        return ResponseEntity.ok().body(new LoginResponse(token));
    }
}
