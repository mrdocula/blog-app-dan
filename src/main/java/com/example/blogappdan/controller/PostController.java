package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Post;
import com.example.blogappdan.exceptions.BusinessException;
import com.example.blogappdan.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPosts(@RequestParam("title") String title,
        @RequestParam("text") String text,
        @RequestParam("userId") int userId) {
        try {
            Post savedPost = postService.createPostForUser(userId, title, text);
            return ResponseEntity.ok(savedPost);
        } catch (BusinessException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<Post> updatePost(@RequestParam("title") String title,
                                            @RequestParam("text") String text,
                                            @PathVariable("postId") int postId) {
        try {
            Post updatedPost = postService.updatePostById(postId, title, text);
            return ResponseEntity.ok(updatedPost);
        } catch (BusinessException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") int postId) {
        Post post = postService.getPostById(postId);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/post")
    public ResponseEntity<List<Post>> getAllPostsByUserId(@PathVariable("userId") int userId) {
        List<Post> posts = postService.getAllPostsByUserId(userId);
        if (posts != null) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable("postId") int postId) {
        try {
            Post deletePost = postService.deletePostById(postId);
            return ResponseEntity.ok(deletePost);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
