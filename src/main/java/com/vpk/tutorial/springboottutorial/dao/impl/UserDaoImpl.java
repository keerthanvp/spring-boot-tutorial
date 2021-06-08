package com.vpk.tutorial.springboottutorial.dao.impl;

import com.vpk.tutorial.springboottutorial.dao.UserDao;
import com.vpk.tutorial.springboottutorial.exception.UserNotFoundException;
import com.vpk.tutorial.springboottutorial.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private static List<User> users = new ArrayList<>();

//    static {
//        users.add(new User(1,"Mark",20));
//        users.add(new User(2,"Steven",26));
//    }

    @Override
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User retrieveUser(Integer userId) {
        for(User user : users){
            if (user.getUserId().equals(userId))
                return user;
        }
        throw new UserNotFoundException("User not found: "+userId);
    }

    @Override
    public List<User> retrieveAllUser() {
        return users;
    }

    @Override
    public User deleteUser(Integer userId) {
        for (User user: users){
            if (user.getUserId().equals(userId)){
                users.remove(user);
                return user;
            }
        }
        throw new UserNotFoundException("User not found: "+userId);
    }

}
