package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.exceptions.BusinessException;
import com.example.blogappdan.exceptions.BusinessExceptionReason;
import com.example.blogappdan.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    private Post post;
    private Post post1;
    private Post post2;
    private User user;
    private Comment comment;

    @BeforeEach
    void setUp(){
        post = new Post("title", "text");
        post1 = new Post("Some post title 1", "Some Post text 1");
        post2 = new Post("Some post title 2", "Some Post text 2");
        user = new User("Tom", "William");
        comment = new Comment(1, "Some text 1", LocalDateTime.now(), post, user);
    }


    @Test
    public void createPost_shouldCreatePostForUser() throws Exception {
        when(postService.createPostForUser(user.getId(), post.getTitle(), post.getPostText())).thenReturn(post);
        mockMvc.perform(
                post("/posts/create")
                        .param("title", "title")
                        .param("text", "text")
                        .param("userId",  String.valueOf(user.getId())))
                .andExpect(status().isOk());
    }

    @Test
    public void createPost_shouldNotCreatePostForUser() throws Exception{
        when(postService.createPostForUser(user.getId(), post.getTitle(), post.getPostText())).thenThrow(
                new BusinessException(BusinessExceptionReason.USER_ID_INVALID));
        mockMvc.perform(
                post("/posts/create")
                        .param("title", "title")
                        .param("text", "text")
                        .param("userId", String.valueOf(user.getId()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void getAllPosts_shouldReturnAllPosts()throws Exception{
        List<Post> allPosts = Arrays.asList(post1, post2);
        when(postService.getAllPosts()).thenReturn(allPosts);

        mockMvc.perform(get("/posts/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPostById_shouldReturnPost()throws Exception{
        when(postService.getPostById(1)).thenReturn(post);
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllCommentsByUserId_shouldAllComments() throws Exception {
        List<Post> posts = Arrays.asList(post1, post2);
        when(postService.getAllPostsByUserId(1)).thenReturn(posts);
        mockMvc.perform(get("/posts/1/post"))
                .andExpect(status().isOk());
    }

/*    //TODO how to use method with return type VOID
    @Test
    public void deletePost_shouldDeletePost() throws Exception{
        when(postService.deletePost(new Post("title", "text"));
    }*/


}
