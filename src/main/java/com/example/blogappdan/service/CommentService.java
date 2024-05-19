package com.example.blogappdan.service;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {


    private final  CommentRepository commentRepository;

    public Comment createOrUpdateComment(String text) {
        Comment comment = new Comment(text);
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
