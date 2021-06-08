package com.vpk.tutorial.springboottutorial.dao;

import com.vpk.tutorial.springboottutorial.model.User;

import java.util.List;

public interface UserDao {
    User createUser(User user);
    User retrieveUser(Integer userId);
    List<User> retrieveAllUser();
    User deleteUser(Integer userId);
}
