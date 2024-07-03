package com.example.blogappdan.repository;

import com.example.blogappdan.entity.Comment;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository <Comment, Integer>{


//TODO could I create methods here

//    List<Comment> findByPostId(int postId);

//    List<Comment> findByUserId(int userId);

}