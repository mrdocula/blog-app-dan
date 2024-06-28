package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.service.PostService;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PostControllerTest {

    /**
     *  @PostMapping("/posts/create")
     *     public ResponseEntity<Post> createPosts(@RequestParam("title") String title,
     *                                            @RequestParam("text") String text){
     *        return ResponseEntity.ok(postService.createPost(title, text));
     *     }
     */

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void createPost_shouldCreatePost() throws Exception {

       // List<Comment> commentList = List.of(new Comment(1, "text", LocalDateTime.now(), new Post()));
        Post post = new Post(1, "title", "text", Arrays.asList(), new User());
        when(postService.createPost(1, "title", "text")).thenReturn(post);
        mockMvc.perform(post("/posts/create")
                        .param("title", "title")
                        .param("text", "text")
                        .param("userId", "1"))
                .andExpect(status().isOk());
    }

  /*  @Test
    public void createComment_shouldCreateComment() throws Exception {
        // when - then
        // when commentService.createOrUpdateCommentForPost(postId, text) then return status ok
        Comment comment = new Comment(1, "Some text", LocalDateTime.now(),
                new Post("Some post title", "Some Post text"));
        when(commentService.createOrUpdateCommentForPost(1, "Some text")).thenReturn(comment);
        mockMvc.perform(
                post("/comments/create")
                        .param("text", "Some text")
                        .param("postId", "1")
        ).andExpect(status().isOk());
    }

    @Test
    public void createComment_shouldNotCreateComment() throws Exception {
        when(commentService.createOrUpdateCommentForPost(1, "Some text")).thenThrow(
                new RuntimeException("Post with ID '1' does not exist!"));
        mockMvc.perform(
                post("/comments/create")
                        .param("text", "Some text")
                        .param("postId", "1")
        ).andExpect(status().isBadRequest());
    }*/
}
