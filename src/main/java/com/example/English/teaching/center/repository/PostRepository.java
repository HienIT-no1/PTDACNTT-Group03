package com.example.English.teaching.center.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.English.teaching.center.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
}
