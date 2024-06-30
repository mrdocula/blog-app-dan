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

    public Comment createOrUpdateCommentForPost(int postId, int userId, String text) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalPost.isPresent() && optionalUser.isPresent()) {
            Comment comment = new Comment(text);
            comment.setPost(optionalPost.get());
            comment.setUser(optionalUser.get());
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Post with ID '" + postId + "' does not exist!");
        }
    }

    public Comment getCommentById(int commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> getAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    public List<Comment> getAllCommentsByPostId(int postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public List<Comment> getAllCommentsByUserId(int userId) {
        return commentRepository.findAllByUserId(userId);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    //  ?   delete comments by post id and user aid

    public Comment deleteCommentFromDatabase(int commentId) throws BusinessException {
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
