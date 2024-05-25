package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comments/create")
    public ResponseEntity<Comment> createComment(@RequestParam("text") String text){
        return ResponseEntity.ok(commentService.createOrUpdateComment(text));
    }
}
