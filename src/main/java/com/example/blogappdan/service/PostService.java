package com.example.blogappdan.service;

import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.exceptions.BusinessException;
import com.example.blogappdan.exceptions.BusinessExceptionReason;
import com.example.blogappdan.repository.PostRepository;
import com.example.blogappdan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPostForUser(int userId, String title, String text) throws BusinessException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Post post = new Post(title, text);
            post.setUser(optionalUser.get());
            return postRepository.save(post);
        } else {
            throw new BusinessException(BusinessExceptionReason.USER_ID_INVALID);
        }
    }

    public Post updatePostById(int postId, String title, String text) throws BusinessException {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(title);
            post.setPostText(text);
            return postRepository.save(post);
        } else {
            throw new BusinessException(BusinessExceptionReason.POST_ID_INVALID);
        }
    }

    public Post getPostById(int postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public List<Post> getAllPosts() {
        return (List<Post>) postRepository.findAll();
    }

    public List<Post> getAllPostsByUserId(int userId) {
        return postRepository.findAllByUserId(userId);
    }

    public Post deletePostById(int postId) throws BusinessException {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postRepository.delete(post);
            return post;
        } else {
            throw new BusinessException(BusinessExceptionReason.POST_ID_INVALID);
        }
    }
}