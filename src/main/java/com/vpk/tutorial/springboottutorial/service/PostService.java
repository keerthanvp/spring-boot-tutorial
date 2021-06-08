package com.vpk.tutorial.springboottutorial.service;

import com.vpk.tutorial.springboottutorial.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(Integer userId, Post post);
    Post retrievePost(Integer userId, Integer postId);
    List<Post> retrieveAllPost(Integer userId);
}
