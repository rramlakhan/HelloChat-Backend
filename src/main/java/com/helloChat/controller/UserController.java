package com.helloChat.controller;

import com.helloChat.entity.User;
import com.helloChat.exception.UserAlreadyExistsException;
import com.helloChat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.CONFLICT);
        }
    }
}
