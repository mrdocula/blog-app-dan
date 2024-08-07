package com.example.blogappdan.controller;

import com.example.blogappdan.entity.User;
import com.example.blogappdan.exceptions.BusinessException;
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

    @PostMapping("/login")
    public ResponseEntity<User> createUser(@RequestParam("name") String name,
                                           @RequestParam("surname") String surname){
        try{
            return ResponseEntity.ok(userService.createUser(name, surname));
        }catch(BusinessException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> updateUser(@RequestParam("oldName") String oldName,
                                           @RequestParam("oldSurname") String oldSurname,
                                           @RequestParam("name") String name,
                                           @RequestParam("surname") String surname){
        try{
            return ResponseEntity.ok(userService.updateUser(oldName, oldSurname, name, surname));
        }catch(BusinessException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") int userId){
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (RuntimeException e) {
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