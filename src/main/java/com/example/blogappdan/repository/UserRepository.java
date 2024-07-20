package com.example.blogappdan.repository;

import com.example.blogappdan.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByNameAndSurname(String name, String surname);

    User findUserByUsername(String username);
}
