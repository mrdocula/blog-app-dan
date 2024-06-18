package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Post;
import com.example.blogappdan.service.PostService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    // final -> помогает убедиться в том, что postService
    // будет передан этому классу, как dependency injection
    private final PostService postService;

    @PostMapping("/posts/create")
    public ResponseEntity<Post> createPosts(@RequestParam("title") String title,
                                           @RequestParam("text") String text,
                                            @RequestParam("userId") int userId){
        try{
            return ResponseEntity.ok(postService.createPost(userId, title, text));
        }catch(RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
      // return ResponseEntity.ok(postService.createPost(title, text));
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

}
