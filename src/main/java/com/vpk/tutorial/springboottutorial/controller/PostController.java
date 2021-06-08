package com.vpk.tutorial.springboottutorial.controller;

import com.vpk.tutorial.springboottutorial.model.Post;
import com.vpk.tutorial.springboottutorial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/post")
    public ResponseEntity<Post> createPost(@PathVariable Integer userId, @RequestBody Post post){
        return new ResponseEntity<>(postService.createPost(userId, post),HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<Post>> retrieveAllPost(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.retrieveAllPost(userId),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<Post> retrievePost(@PathVariable Integer userId, @PathVariable Integer postId){
        return new ResponseEntity<>(postService.retrievePost(userId, postId), HttpStatus.OK);
    }
}
