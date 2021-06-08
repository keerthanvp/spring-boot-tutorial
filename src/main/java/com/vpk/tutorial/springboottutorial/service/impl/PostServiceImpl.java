package com.vpk.tutorial.springboottutorial.service.impl;

import com.vpk.tutorial.springboottutorial.dao.PostRepository;
import com.vpk.tutorial.springboottutorial.dao.UserRepository;
import com.vpk.tutorial.springboottutorial.exception.InvalidPostIDException;
import com.vpk.tutorial.springboottutorial.exception.UserNotFoundException;
import com.vpk.tutorial.springboottutorial.model.Post;
import com.vpk.tutorial.springboottutorial.model.User;
import com.vpk.tutorial.springboottutorial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createPost(Integer userId, Post post) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
            throw new UserNotFoundException("User Not Found: "+userId);
        post.setUser(userOptional.get());
        return postRepository.save(post);
    }

    @Override
    public Post retrievePost(Integer userId, Integer postId) {
        Post post = postRepository.getById(postId);
        System.out.println(post.toString());
//        if (!post.getUser().getUserId().equals(userId))
//            throw new InvalidPostIDException("Invalid Access to postId: "+postId);
        return post;
    }

    @Override
    public List<Post> retrieveAllPost(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
            throw new UserNotFoundException("User Not Found: "+userId);
        return userOptional.get().getPosts();
    }
}
