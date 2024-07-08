package com.example.blogappdan.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.exceptions.BusinessException;
import com.example.blogappdan.exceptions.BusinessExceptionReason;
import com.example.blogappdan.service.CommentService;
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

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    private Comment comment1;
    private Comment comment2;
    private User user;
    private Post post;

    @BeforeEach
    void setUp() {
        user = new User("Tom", "tom");
        post = new Post("Some post title", "Some Post text");
        comment1 = new Comment(1, "Some text 1", LocalDateTime.now(), post, user);
        comment2 = new Comment(2, "Some text 2", LocalDateTime.now(), post, user);
    }

    @Test
    public void createCommentForPost_shouldComment()throws Exception{
        when(commentService.createCommentForPost(post.getPostId(),user.getId(), "Some text")).thenReturn(comment1);

        mockMvc.perform(
                post("/comments/create")
                        .param("postId", String.valueOf(post.getPostId()))
                        .param("userId", String.valueOf(user.getId()))
                        .param("text", "Some text"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCommentForPost_shouldReturnComment()throws Exception{
        when(commentService.updateComment(1, "Some text")).thenReturn(comment1);

        mockMvc.perform(
                        post("/comments/update/1")
                                .param("commentId", String.valueOf(comment1.getCommentId()))
                                .param("text", "Some text"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCommentForPost_shouldReturnNotFound()throws Exception{
        when(commentService.updateComment(1, "Some text")).thenThrow(new BusinessException(BusinessExceptionReason.COMMENT_ID_INVALID));

        mockMvc.perform(
                        post("/comments/update/1")
                                .param("commentId", String.valueOf(comment1.getCommentId()))
                                .param("text", "Some text"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllComments_shouldReturnAllComments() throws Exception {
        List<Comment> allComments = Arrays.asList(comment1, comment2);
        when(commentService.getAllComments()).thenReturn(allComments);

        mockMvc.perform(get("/comments/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCommentById_shouldReturnComment() throws Exception {
        when(commentService.getCommentById(1)).thenReturn(comment1);

        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllCommentsByPostId_shouldReturnComments() throws Exception {
        List<Comment> comments = Arrays.asList(comment1, comment2);
        when(commentService.getAllCommentsByPostId(1)).thenReturn(comments);

        mockMvc.perform(get("/comments/post/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllCommentsByUserId_shouldReturnComments() throws Exception {
        List<Comment> comments = Arrays.asList(comment1, comment2);
        when(commentService.getAllCommentsByUserId(user.getId())).thenReturn(comments);

        mockMvc.perform(get("/comments/user/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteComment_shouldDeleteComment() throws Exception {
        when(commentService.deleteCommentByCommentId(1)).thenReturn(comment1);
        mockMvc.perform(delete("/comments/delete/1")
                        .param("commentId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCommentNotFound_shouldReturnBadRequest() throws Exception {
        when(commentService.deleteCommentByCommentId(1)).thenThrow(new RuntimeException("Comment not found"));
        mockMvc.perform(delete("/comments/delete/1")
                        .param("commentId", "1"))
                .andExpect(status().isBadRequest());
    }
}