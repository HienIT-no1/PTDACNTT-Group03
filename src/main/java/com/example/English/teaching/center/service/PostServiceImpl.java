package com.example.English.teaching.center.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.English.teaching.center.model.Post;
import com.example.English.teaching.center.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getNewPosts() {
        List<Post.PostType> types = Arrays.asList(Post.PostType.NEWS, Post.PostType.EVENT);

        return postRepository.findByTypeInOrderByPublishedAtDesc(types);
    }

    @Override
    public List<Post> getNewPostBlogs() {
        List<Post.PostType> type = Arrays.asList(Post.PostType.BLOG);

        return postRepository.findByTypeInOrderByPublishedAtDesc(type);
    }

    @Override
    public Post getPostBySlug(String slug) {
        return postRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết với slug: " + slug));
    }
}
