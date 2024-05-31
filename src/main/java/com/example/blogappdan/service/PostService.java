package com.example.blogappdan.service;

import com.example.blogappdan.entity.Post;
import com.example.blogappdan.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(String title, String text){
        return postRepository.save(new Post(title, text));
    }
}
