package com.example.blogappdan.service;

import com.example.blogappdan.entity.User;
import com.example.blogappdan.exceptions.BusinessException;
import com.example.blogappdan.exceptions.BusinessExceptionReason;
import com.example.blogappdan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createOrUpdateUser(String name, String surname) {
        // TODO: separate create and update functionalities (update by user ID)
        return null;
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User deleteUserById(int userId) throws BusinessException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
            return user;
        } else {
            throw new BusinessException(BusinessExceptionReason.USER_ID_INVALID);
        }
    }
}