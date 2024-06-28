package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestParam("text") String text,
                                                 @RequestParam("userId") int userId,
                                                 @RequestParam("postId") int postId) {
        try {
            Comment savedComment = commentService.createOrUpdateCommentForPost(postId, userId, text);
            return ResponseEntity.ok(savedComment);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list")
    public List<Comment> getAllComment() {
        return commentService.getAllComment();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Comment> deleteComment(@RequestParam("commentId") int commentId){
        try{
            Comment deleteComment = commentService.deleteCommentFromDatabase(commentId);
            return ResponseEntity.ok(deleteComment);
        }catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    // TODO: add an endpoint to delete comment
    // TODO: subtask - create method in CommentService to remove comment from the database
    // Make sure to throw and catch exceptions


}
