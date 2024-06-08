package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

   private final CommentService commentService;

    @PostMapping("/comments/create")
    public ResponseEntity<Comment> createComment(@RequestParam("text") String text, @RequestParam("postId") int postId){
        return ResponseEntity.ok(commentService.createOrUpdateCommentForPost(postId, text));
    }

    // TODO: add endpoint to get all comments - done
    @GetMapping("/comments")
    public List<Comment> getAllComment(){
        return commentService.getAllComment();
    }


}
