package com.example.blogappdan.service;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.entity.Post;
import com.example.blogappdan.repository.CommentRepository;
import com.example.blogappdan.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public Comment createOrUpdateCommentForPost(int postId, String text) {
        Post postToAddComment = postRepository.findById(postId).get();
        Comment comment = new Comment(text);
        comment.setPost(postToAddComment);
        return commentRepository.save(comment);
    }

    public Comment getCommentById(int id){
        return commentRepository.findById(id).orElse(null);
    }

    public void deleteComment(Comment comment){
        commentRepository.delete(comment);

    }

    public List<Comment> getAllComment(){
        return (List<Comment>) commentRepository.findAll();
    }
}
