package com.example.blogappdan.controller;

import com.example.blogappdan.entity.User;
import com.example.blogappdan.service.UserService;
import org.apache.catalina.startup.Tomcat;
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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void createUser_shouldCreateUser()throws Exception{
        User user = new User("Tom", "William");
        when(userService.createOrUpdateUser("Tom", "William")).thenReturn(user);
        mockMvc.perform(
                post("/users/create")
                    .param("name", "Tom")
                    .param("surname", "William"))
                .andExpect(status().isOk());
    }

    @Test
    public void createUser_shouldNotCreateUser() throws  Exception{

        User user = new User("Tom", "William");
        when(userService.createOrUpdateUser("Tom", "William")).thenThrow(
                new RuntimeException("User " + user.getName() + " does mot exist!"));
        mockMvc.perform(
                post("/users/create")
                        .param("name", "Tom")
                        .param("surname", "William"))
                .andExpect(status().isBadRequest());

    }
    //
    //    @Test
    //    public void createPost_shouldNotCreatePostForUser() throws Exception{
    //
    //        User user = new User("Tom", "tom");
    //        when(postService.createPostForUser(user.getId(), "Some title" , "Some text")).thenThrow(
    //                new RuntimeException("User with id: " + user.getId() + " does not exist!"));
    //        mockMvc.perform(
    //                post("/posts/create")
    //                        .param("title", "Some title")
    //                        .param("text", "Some text")
    //                        .param("userId", String.valueOf(user.getId()))
    //        ).andExpect(status().isBadRequest());
    //    }
}
