package com.vpk.tutorial.springboottutorial.service.impl;

import com.vpk.tutorial.springboottutorial.dao.UserDao;
import com.vpk.tutorial.springboottutorial.dao.UserRepository;
import com.vpk.tutorial.springboottutorial.exception.UserNotFoundException;
import com.vpk.tutorial.springboottutorial.model.User;
import com.vpk.tutorial.springboottutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> retrieveUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent())
            throw new UserNotFoundException("User Not Found: "+userId);
        return user;
    }

    @Override
    public List<User> retrieveAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
