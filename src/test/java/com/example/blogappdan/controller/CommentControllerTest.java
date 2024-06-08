package com.example.blogappdan.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.entity.Post;
import com.example.blogappdan.service.CommentService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CommentControllerTest {

    /**
     * @PostMapping("/comments/create") public ResponseEntity<Comment> createComment(@RequestParam("text") String text,
     * @RequestParam("postId") int postId) { try { return
     * ResponseEntity.ok(commentService.createOrUpdateCommentForPost(postId, text)); } catch (RuntimeException ex) {
     * return ResponseEntity.badRequest().build(); } }
     */

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
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
    }

}
