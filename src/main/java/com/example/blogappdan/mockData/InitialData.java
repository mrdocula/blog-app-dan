//package com.example.blogappdan.mockData;
//
//import com.example.blogappdan.entity.Post;
//import com.example.blogappdan.entity.User;
//import com.example.blogappdan.service.CommentService;
//import com.example.blogappdan.service.PostService;
//import com.example.blogappdan.service.UserService;
//import jakarta.annotation.PostConstruct;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//@Slf4j
//public class InitialData {
//
//    private CommentService commentService;
//    private UserService userService;
//    private PostService postService;
//
//    @PostConstruct
//    public void initializeData() {
//        userService.createOrUpdateUser("Dani", "Mani");
//
//        log.info("Request received to create mock data.");
//
//        User user = userService.createOrUpdateUser("Dan", "Dow");
//        if(user != null) {
//            postService.createPostForUser(user.getId(), "Boow", "Woow");
//            postService.createPostForUser(user.getId(), "Hooo", "Mooo");
//            postService.createPostForUser(user.getId(), "Lumpa", "Lumpa");
//
//            Post post = postService.createPostForUser(user.getId(), "Java", "New version.");
//            if (post != null) {
//                commentService.createOrUpdateCommentForPost(post.getPostId(), user.getId(), "Great JAVA!!!");
//                commentService.createOrUpdateCommentForPost(post.getPostId(), user.getId(), "My comment 9.");
//                commentService.createOrUpdateCommentForPost(post.getPostId(), user.getId(), "My comment 5.");
//            }
//        }
//    }
//}


package com.example.blogappdan.mockData;

import com.example.blogappdan.service.CommentService;
import com.example.blogappdan.service.PostService;
import com.example.blogappdan.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@AllArgsConstructor
@Slf4j
public class InitialData {

    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;

    //TODO don't work with
    @PostConstruct
    public void initializeData() {
        //userService.createOrUpdateUser("Dani", "Mani");

        //log.info("Request received to create mock data.");

//        User user = userService.createOrUpdateUser("Dan", "Dow");
//        if (user != null) {
//            postService.createPostForUser(user.getId(), "Boow", "Woow");
//            postService.createPostForUser(user.getId(), "Hooo", "Mooo");
//            postService.createPostForUser(user.getId(), "Lumpa", "Lumpa");
//
//            Post post = postService.createPostForUser(user.getId(), "Java", "New version.");
//            if (post != null) {
//                commentService.createOrUpdateCommentForPost(post.getPostId(), user.getId(), "Great JAVA!!!");
//                commentService.createOrUpdateCommentForPost(post.getPostId(), user.getId(), "My comment 9.");
//                commentService.createOrUpdateCommentForPost(post.getPostId(), user.getId(), "My comment 5.");
//            }
//        }
   }
}

