package com.example.blogappdan.service;

import com.example.blogappdan.entity.User;
import com.example.blogappdan.exceptions.BusinessException;
import com.example.blogappdan.exceptions.BusinessExceptionReason;
import com.example.blogappdan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(String name, String password) throws BusinessException{
        // TODO: separate create and update functionalities (update by user ID)
        User user = new User(name, bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User  updateUser(String oldName, String oldSurname, String name, String surname) throws BusinessException{
        // TODO: separate create and update functionalities (update by user ID)
        Optional<User> optionalUser = userRepository.findByNameAndSurname(oldName, oldSurname);
        if (optionalUser.isPresent()){
           User user = optionalUser.get();
           user.setName(name);
           user.setSurname(surname);
           return userRepository.save(user);
        }else{
            throw new BusinessException(BusinessExceptionReason.USER_ID_INVALID);
        }
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

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}