package com.example.blogappdan.mockData;

import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.service.CommentService;
import com.example.blogappdan.service.PostService;
import com.example.blogappdan.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class InitialData {

    // TODO: add more data here - create user, create post for user and create 2 comments for post

    private CommentService commentService;
    private UserService userService;
    private PostService postService;

    @PostConstruct
    public void initializeData() {
        userService.createOrUpdateUser("Dani", "Mani");

        log.info("Request received to create mock data.");

        User user = userService.createOrUpdateUser("Dan", "Dow");
        postService.createPost(user.getId() ,"Boow", "Woow");
        postService.createPost(user.getId() ,"Hooo", "Mooo");
        postService.createPost(user.getId() ,"Lumpa", "Lumpa");

        Post post = postService.createPost(user.getId() ,"Java", "New version.");
        if (post != null) {
            commentService.createOrUpdateCommentForPost(post.getPostId(), user.getId(),"Great JAVA!!!");
            commentService.createOrUpdateCommentForPost(post.getPostId(),  user.getId(),"My comment 9.");
            commentService.createOrUpdateCommentForPost(post.getPostId(),  user.getId(),"My comment 5.");
        }


    }

}
