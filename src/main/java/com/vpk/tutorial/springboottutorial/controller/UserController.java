package com.vpk.tutorial.springboottutorial.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.vpk.tutorial.springboottutorial.model.Post;
import com.vpk.tutorial.springboottutorial.model.User;
import com.vpk.tutorial.springboottutorial.service.PostService;
import com.vpk.tutorial.springboottutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> retrieveAllUser(){
        return new ResponseEntity<>(userService.retrieveAllUser(),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public EntityModel<User> retrieveUser(@PathVariable Integer userId){
        Optional<User> user = userService.retrieveUser(userId);
        EntityModel<User> model = EntityModel.of(user.get());
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUser());
        model.add(linkBuilder.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/post")
    public ResponseEntity<Post> createPost(@PathVariable Integer userId, @RequestBody Post post){
        return new ResponseEntity<>(postService.createPost(userId, post),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<Post>> retrieveAllPost(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.retrieveAllPost(userId),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<Post> retrievePost(@PathVariable Integer userId, @PathVariable Integer postId){
        return new ResponseEntity<>(postService.retrievePost(userId, postId),HttpStatus.OK);
    }

}
