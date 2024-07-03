package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.exceptions.BusinessException;
import com.example.blogappdan.service.CommentService;
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
        } catch (BusinessException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") int commentId){
        Comment comment = commentService.getCommentById(commentId);
        if (comment != null){
            return ResponseEntity.ok(comment);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getAllCommentsByPostId(@PathVariable("postId") int postId){
        List<Comment> comments = commentService.getAllCommentsByPostId(postId);
        if (comments != null){
            return ResponseEntity.ok(comments);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getAllCommentsByUserId(@PathVariable("userId") int userId) throws BusinessException {
        List<Comment> comments = commentService.getAllCommentsByUserId(userId);
        if (comments != null){
            return ResponseEntity.ok(comments);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("commentId") int commentId){
        try{
            Comment deleteComment = commentService.deleteCommentByCommentId(commentId);
            return ResponseEntity.ok(deleteComment);
        }catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

}



//package com.example.blogappdan.controller;
//
//import com.example.blogappdan.entity.Comment;
//import com.example.blogappdan.service.CommentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/comments")
//public class CommentController {
//
//    private final CommentService commentService;
//
//    @PostMapping("/create")
//    public ResponseEntity<Comment> createComment(@RequestParam("text") String text,
//                                                 @RequestParam("userId") int userId,
//                                                 @RequestParam("postId") int postId) {
//        try {
//            Comment savedComment = commentService.createOrUpdateCommentForPost(postId, userId, text);
//            return ResponseEntity.ok(savedComment);
//        } catch (RuntimeException ex) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @GetMapping("/list")
//    public List<Comment> getAllComments() {
//        return commentService.getAllComments();
//    }
//
//    @GetMapping("/{commentId}")
//    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") int commentId){
//        Comment comment = commentService.getCommentById(commentId);
//        if (comment != null){
//            return ResponseEntity.ok(comment);
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @GetMapping("/{postId}/comment")
//    public ResponseEntity<List<Comment>> getAllCommentsByPostId(@PathVariable("postId") int postId){
//        List<Comment> comments = commentService.getAllCommentsByPostId(postId);
//        if (comments != null && !comments.isEmpty()){
//            return ResponseEntity.ok(comments);
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @GetMapping("/{userId}/user-comments")
//    public ResponseEntity<List<Comment>> getAllCommentsByUserId(@PathVariable("userId") int userId){
//        List<Comment> comments = commentService.getAllCommentsByUserId(userId);
//        if (comments != null && !comments.isEmpty()){
//            return ResponseEntity.ok(comments);
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @DeleteMapping("/delete/{commentId}")
//    public ResponseEntity<Comment> deleteComment(@PathVariable("commentId") int commentId){
//        try{
//            Comment deletedComment = commentService.deleteCommentFromDatabase(commentId);
//            return ResponseEntity.ok(deletedComment);
//        } catch(Exception ex){
//            return ResponseEntity.badRequest().build();
//        }
//    }
//}