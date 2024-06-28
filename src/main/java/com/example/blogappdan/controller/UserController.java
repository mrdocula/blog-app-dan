package com.example.blogappdan.controller;

import com.example.blogappdan.entity.User;
import com.example.blogappdan.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUsers(@RequestParam String name,
        @RequestParam String username) {
        return ResponseEntity.ok(userService.createOrUpdateUser(name, username));
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
