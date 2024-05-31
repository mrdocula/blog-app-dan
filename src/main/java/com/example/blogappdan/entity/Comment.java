package com.example.blogappdan.entity;

import com.example.blogappdan.service.PostService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name="blog-comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;
    private String text;
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String text){
        this.text = text;
        time = LocalDateTime.now();
    }
}
