package com.georgij.pw3Task2.service;

import com.georgij.pw3Task2.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post createPost(Post post);
    Optional<Post> updatePost(Long id, Post post);
    void deletePost(Long id);
}
