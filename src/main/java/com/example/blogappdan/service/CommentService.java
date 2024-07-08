package com.example.blogappdan.service;

import com.example.blogappdan.entity.Comment;
import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.exceptions.BusinessExceptionReason;
import com.example.blogappdan.exceptions.BusinessException;
import com.example.blogappdan.repository.CommentRepository;
import com.example.blogappdan.repository.PostRepository;
import java.util.Optional;

import com.example.blogappdan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment createCommentForPost(int postId, int userId, String text) throws BusinessException {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalPost.isEmpty()) {
            throw new BusinessException(BusinessExceptionReason.POST_ID_INVALID);
        }
        if (optionalUser.isEmpty()) {
            throw new BusinessException(BusinessExceptionReason.USER_ID_INVALID);
        }

        Comment comment = new Comment(text);
        comment.setPost(optionalPost.get());
        comment.setUser(optionalUser.get());
        return commentRepository.save(comment);
    }

    public Comment updateComment(int commentId, String text) throws BusinessException{
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()){
            Comment comment = optionalComment.get();
            comment.setText(text);
            return commentRepository.save(comment);
        }else{
            throw new BusinessException(BusinessExceptionReason.COMMENT_ID_INVALID);
        }
    }

    public Comment getCommentById(int commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> getAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    public List<Comment> getAllCommentsByPostId(int postId) throws BusinessException{
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()){
            return optionalPost.get().getCommentList();
        }else{
            throw new BusinessException(BusinessExceptionReason.POST_ID_INVALID);
        }
    }

    public List<Comment> getAllCommentsByUserId(int userId) throws BusinessException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            return optionalUser.get().getCommentList();
        }else{
            throw new BusinessException(BusinessExceptionReason.USER_ID_INVALID);
        }
    }

    public Comment deleteCommentByCommentId(int commentId) throws BusinessException {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            commentRepository.delete(comment);
            return comment;
        } else {
            throw new BusinessException(BusinessExceptionReason.COMMENT_ID_INVALID);
        }
    }
}