package com.vpk.tutorial.springboottutorial.service;

import com.vpk.tutorial.springboottutorial.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Test
    public void retrieveUser_UserNotFoundTest() {
        assertThrows(UserNotFoundException.class,()->{

        });
    }
}
