package com.example.blogappdan.controller;

import com.example.blogappdan.entity.User;
import com.example.blogappdan.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO: finish up declaration of this controller - done
@RestController
@RequiredArgsConstructor
public class UserController {

    // TODO: add endpoint to get all posts by user id - done
    private final UserService userService;

    @PostMapping("/users/create")
    public ResponseEntity<User> createUsers(@RequestParam String name,
                                            @RequestParam String username){
        return ResponseEntity.ok(userService.createOrUpdateUser(name, username));
    }


    // @PostMapping("/comments/create")
    //    public ResponseEntity<Comment> createComment(@RequestParam("text") String text,
    //        @RequestParam("postId") int postId) {
    //        try {
    //            return ResponseEntity.ok(commentService.createOrUpdateCommentForPost(postId, text));
    //        } catch (RuntimeException ex) {
    //            return ResponseEntity.badRequest().build();
    //        }
    //    }



    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
