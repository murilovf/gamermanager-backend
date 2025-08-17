package com.fernandes.gamermanager.java.controller;

import com.fernandes.gamermanager.java.documents.UserDocument;
import com.fernandes.gamermanager.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDocument> register(@RequestBody UserDocument userDocument){
        UserDocument userSave = userService.save(userDocument);
        return ResponseEntity.ok().body(userSave);
    }
}
