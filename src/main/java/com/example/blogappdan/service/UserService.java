package com.example.blogappdan.service;

import com.example.blogappdan.entity.Post;
import com.example.blogappdan.entity.User;
import com.example.blogappdan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Связать User и Comment классы в сервисе и в контролере, так чтобы для создания comment,
// нужно было бы указать id юзера и id поста

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createOrUpdateUser(String name, String username){
        User user = new User(name, username);
        return userRepository.save(user);
    }

//    public Post createPost(int userId, String title, String text){
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isPresent()){
//            Post post = new Post(title, text);
//            post.setUser(optionalUser.get());
//            return postRepository.save(post);
//        }else{
//            throw new RuntimeException("User with ID '" + userId + "' does not exist!");
//        }
//
//    }



    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }
}
