package com.example.blogappdan.controller;

import com.example.blogappdan.entity.User;
import com.example.blogappdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<User> createUsers(@RequestParam("name") String name,
                                            @RequestParam("surname") String username) {
        try{
            return ResponseEntity.ok(userService.createOrUpdateUser(name, username));
        }catch(RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if (user != null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public void deleteUser(User user){
        userService.deleteUser(user);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<User> deleteUserById(@PathVariable("userId") int userId){
        try{
            User deleteUser = userService.deleteUserById(userId);
            return ResponseEntity.ok(deleteUser);
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }


}