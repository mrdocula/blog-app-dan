package com.example.blogappdan.service;

import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.repository.PostRepository;
import com.example.blogappdan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(int userId, String title, String text){
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            Post post = new Post(title, text);
            post.setUser(optionalUser.get());
            return postRepository.save(post);
        }else{
            throw new RuntimeException("User with ID '" + userId + "' does not exist!");
        }


        //return postRepository.save(new Post(title, text));
    }
}
