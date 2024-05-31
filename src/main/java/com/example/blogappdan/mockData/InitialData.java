package com.example.blogappdan.mockData;

import com.example.blogappdan.service.CommentService;
import com.example.blogappdan.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class InitialData {

    // TODO: add more data here - create user, create post for user and create 2 comments for post

    private static final Logger log = LoggerFactory.getLogger(InitialData.class);
    private  CommentService commentService;
    private  UserService userService;
    @PostConstruct
    public void initializeData(){
        log.info("Request received to create mock data.");
       commentService.createOrUpdateComment("Great Job!!!");
        userService.createOrUpdateUser("Dan", "martian");

    }

}
