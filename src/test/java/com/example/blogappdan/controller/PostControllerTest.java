package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
    public void createPost_shouldCreatePostForUser() throws Exception {

        Post post = new Post(1, "title", "text", null, null);
        when(postService.createPostForUser(1, "title", "text")).thenReturn(post);
        mockMvc.perform(
                post("/posts/create")
                        .param("title", "title")
                        .param("text", "text")
                        .param("userId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void createPost_shouldNotCreatePostForUser() throws Exception{

        User user = new User("Tom", "tom");
        when(postService.createPostForUser(user.getId(), "Some title" , "Some text")).thenThrow(
                new RuntimeException("User with id: " + user.getId() + " does not exist!"));
        mockMvc.perform(
                post("/posts/create")
                        .param("title", "Some title")
                        .param("text", "Some text")
                        .param("userId", String.valueOf(user.getId()))
        ).andExpect(status().isBadRequest());
    }

}
