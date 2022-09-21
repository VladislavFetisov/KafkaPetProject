package com.example.producer.controller;

import com.example.producer.model.User;
import com.example.producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.sendUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
