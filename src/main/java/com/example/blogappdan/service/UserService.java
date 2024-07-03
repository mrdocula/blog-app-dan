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

    public User createOrUpdateUser(String name, String surname){
        User user = new User(name, surname);
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent()){
           return userRepository.save(user);
        }else{
            //TODO how can use Exception with this method update or create
            //TODO which Exceprion to use here?
            throw new RuntimeException("User not found or User already created");
        }
    }

    public User getUserById(int userId){
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }
    //TODO need create method getAllUserByPostID

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public User deleteUserById(int userId)throws BusinessException{
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            userRepository.delete(user);
            return user;
        }else{
            throw new BusinessException(BusinessExceptionReason.USER_ID_INVALID);
        }
    }
}