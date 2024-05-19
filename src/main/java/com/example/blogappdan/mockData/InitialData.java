package com.example.blogappdan.mockData;

import com.example.blogappdan.service.CommentService;
import com.example.blogappdan.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class InitialData {

    private final CommentService commentService;
    private final UserService userService;
    @PostConstruct
    public void initializeData(){
        log.info("Request received to create mock data.");
       commentService.createOrUpdateComment("Great Job!!!");
        userService.createOrUpdateUser("Dan", "martian");

    }

}
