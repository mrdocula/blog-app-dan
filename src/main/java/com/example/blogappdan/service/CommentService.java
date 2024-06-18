package com.example.blogappdan.service;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.repository.CommentRepository;
import com.example.blogappdan.repository.PostRepository;
import java.util.Optional;

import com.example.blogappdan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public Comment createOrUpdateCommentForPost(int postId, String text) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Comment comment = new Comment(text);
            comment.setPost(optionalPost.get());
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Post with ID '" + postId + "' does not exist!");
        }
    }

//    public Comment createOrUpdateCommentForUser(int userId, int postId, String text) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        Optional<Post> optionalPost = postRepository.findById(postId);
//        if (optionalPost.isPresent() && optionalUser.isPresent()) {
//            Comment comment = new Comment(text);
//            comment.setPost(optionalPost.get());
//            comment.setUser(optionalUser.get());
//            return commentRepository.save(comment);
//        } else {
//            throw new RuntimeException("Post with ID '" + postId + "' and User with ID '" + userId + "' does not exist!");
//        }
//    }


    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);

    }

    public List<Comment> getAllComment() {
        return (List<Comment>) commentRepository.findAll();
    }
}
