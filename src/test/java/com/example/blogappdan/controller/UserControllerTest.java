package com.example.blogappdan.controller;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.exceptions.BusinessException;
import com.example.blogappdan.exceptions.BusinessExceptionReason;
import com.example.blogappdan.service.UserService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private User user1;
    private User user2;
    private Comment comment;
    private Post post;

    @BeforeEach
    void setUp(){
        user1 = new User("Tom", "William");
        user2 = new User("Bill", "Clinton");
        post = new Post("Some post title", "Some Post text");
        comment = new Comment(1, "Some text 1", LocalDateTime.now(), post, user1);
    }

    @Test
    public void createUser_shouldCreateUser()throws Exception{
        when(userService.createUser("Tom", "William")).thenReturn(user1);
        mockMvc.perform(
                post("/users/create")
                    .param("name", "Tom")
                    .param("surname", "William"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser_shouldUpdateUser()throws Exception{
        when(userService.updateUser("Tom", "William", "Jack", "Russell")).thenReturn(user1);
        mockMvc.perform(
                post("/users/update")
                        .param("oldName", "Tom")
                        .param("oldSurname", "William")
                        .param("name", "Jack")
                        .param("surname", "Russell"))
                .andExpect(status().isOk());
    }

    @Test
    public void  updateUser_shouldNotUpdateUser() throws Exception{
        when(userService.updateUser("Tom", "William", "Jack", "Russell"))
                .thenThrow(new BusinessException(BusinessExceptionReason.USER_ID_INVALID));
        mockMvc.perform(
                post("/users/update")
                        .param("oldName", "Tom")
                        .param("oldSurname", "William")
                        .param("name", "Jack")
                        .param("surname", "Russell"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllUsers_shouldReturnAllUsers()throws Exception{
        List<User> allUsers = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(allUsers);

        mockMvc.perform(get("/users/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserById_shouldReturnUser()throws Exception{
        when(userService.getUserById(1)).thenReturn(user1);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserById_shouldNotReturnUser()throws Exception{
        when(userService.getUserById(1)).thenThrow(
                new RuntimeException("Does not"));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteUserById_shouldReturnUser()throws Exception{
        when(userService.deleteUserById(1)).thenReturn(user1);

        mockMvc.perform(delete("/users/delete/1")
                        .param("userId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserBuId_shouldReturnBadRequest()throws Exception{
        when(userService.deleteUserById(1)).thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(delete("/users/delete/1")
                .param("userId", "1"))
                .andExpect(status().isNotFound());
    }
}