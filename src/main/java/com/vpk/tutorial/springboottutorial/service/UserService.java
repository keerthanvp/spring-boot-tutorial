package com.vpk.tutorial.springboottutorial.service;

import com.vpk.tutorial.springboottutorial.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> retrieveUser(Integer userId);
    List<User> retrieveAllUser();
    void deleteUser(Integer userId);
}
