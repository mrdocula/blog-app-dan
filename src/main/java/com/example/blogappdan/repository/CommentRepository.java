package com.example.blogappdan.repository;

import com.example.blogappdan.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository <Comment, Integer>{
    List<Comment> findAllByPostId(int postId);
    List<Comment> findAllByUserId(int userId);
}