package com.example.English.teaching.center.service;

import java.util.List;
import java.util.Optional;

import com.example.English.teaching.center.model.Post;

public interface PostService {

//-----------------------------Sử lý post bên admin -----------------------------
    List<Post> getAllPosts();

    void deletePostById(Long id);

    void savePost(Post post);

    Optional<Post> getPostById(Long id);

//-----------------------------Sử lý post bên user -----------------------------

    List<Post> getNewPosts();

    List<Post> getNewPostBlogs();

    Post getPostBySlug(String slug);

}
