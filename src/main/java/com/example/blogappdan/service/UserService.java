package com.example.blogappdan.service;

import com.example.blogappdan.entity.User;
import com.example.blogappdan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createOrUpdateUser(String name, String username){
        User user = new User(name, username);
        return userRepository.save(user);
    }

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
