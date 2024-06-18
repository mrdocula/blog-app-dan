package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/create")
    public ResponseEntity<Comment> createComment(@RequestParam("text") String text,
                                                 @RequestParam("postId") int postId) {
        try {
            return ResponseEntity.ok(commentService.createOrUpdateCommentForPost(postId, text));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @PostMapping("/comments/create")
//    public ResponseEntity<Comment> createComment(@RequestParam("text") String text,
//                                                 @RequestParam("postId") int postId,
//                                                @RequestParam("userId") int userId) {
//        try {
//            return ResponseEntity.ok(commentService.createOrUpdateCommentForUser(userId,postId, text));
//        } catch (RuntimeException ex) {
//            return ResponseEntity.badRequest().build();
//        }
//    }


    // TODO: add endpoint to get all comments - done
    @GetMapping("/comments")
    public List<Comment> getAllComment() {
        return commentService.getAllComment();
    }

}
