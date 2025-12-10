package com.example.English.teaching.center.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.English.teaching.center.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByTypeInOrderByPublishedAtDesc(List<Post.PostType> types);

    Optional<Post> findBySlug(String slug);
}
