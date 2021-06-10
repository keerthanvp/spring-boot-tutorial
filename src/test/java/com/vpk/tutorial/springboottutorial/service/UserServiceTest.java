package com.vpk.tutorial.springboottutorial.service;

import com.vpk.tutorial.springboottutorial.dao.UserRepository;
import com.vpk.tutorial.springboottutorial.exception.UserNotFoundException;
import com.vpk.tutorial.springboottutorial.model.User;
import com.vpk.tutorial.springboottutorial.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user1;
    private User user2;
    private List<User> usersList;

    @BeforeEach
    public void setUp(){
        user1 = new User(1, "Mathew", 23, null);
        user2 = new User(2,"Benjamin",28,null);
        usersList = new ArrayList<>();
        usersList.add(user1);
        usersList.add(user2);
    }

    @Test
    public void createUserTest(){
        when(userRepository.save(user1)).thenReturn(user1);
        assertEquals(user1,userService.createUser(user1));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void retrieveUserTest(){
        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2)).thenReturn(Optional.of(user2));
        assertEquals(user1,userService.retrieveUser(user1.getUserId()).get());
        assertEquals(user2,userService.retrieveUser(user2.getUserId()).get());
        assertNotEquals(user1,userService.retrieveUser(user2.getUserId()).get());
        verify(userRepository,times(3)).findById(any());
    }

    @Test
    public void retrieveUser_UserNotFoundTest() {
        when(userRepository.findById(3)).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class,()->{
            userService.retrieveUser(3);
        });
    }

    @Test
    public void retrieveAllUserTest(){
        when(userRepository.findAll()).thenReturn(usersList);
        assertEquals(usersList,userService.retrieveAllUser());
        verify(userRepository,times(1)).findAll();
    }

    @Test
    public void deleteUserTest(){
        userService.deleteUser(anyInt());
        verify(userRepository,times(1)).deleteById(any());
    }

}
