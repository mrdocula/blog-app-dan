package com.example.blogappdan.service;

import com.example.blogappdan.entity.Post;
import com.example.blogappdan.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(String title, String text){
        return postRepository.save(new Post(title, text));
    }
}
