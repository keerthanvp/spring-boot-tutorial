package com.vpk.tutorial.springboottutorial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpk.tutorial.springboottutorial.model.User;
import com.vpk.tutorial.springboottutorial.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;
    private List<User> userList;

    @BeforeEach
    public void setUp() {
        user = new User(1, "Mathew", 23, null);
        userList = new ArrayList<>();
        userList.add(user);
        userList.add(new User(2,"Benjamin",28,null));
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    public void tearDown() {
        user = null;
        userList.clear();
    }

    @Test
    public void createUserTest() throws Exception {
        when(userService.createUser(any())).thenReturn(user);
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(user)))
                .andDo(MockMvcResultHandlers.print());

        verify(userService, times(1)).createUser(any());
    }

    @Test
    public void retrieveAllUserTest() throws Exception {
        when(userService.retrieveAllUser()).thenReturn(userList);
        mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(userList)))
                .andDo(MockMvcResultHandlers.print());
        verify(userService).retrieveAllUser();
        verify(userService, times(1)).retrieveAllUser();
    }

    @Test
    public void retrieveUserTest() throws Exception {
        when(userService.retrieveUser(user.getUserId())).thenReturn(Optional.of(user));
        mockMvc.perform(get("/user/"+user.getUserId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(user)))
                .andDo(MockMvcResultHandlers.print());
        verify(userService, times(1)).retrieveUser(user.getUserId());
    }

    @Test
    public void deleteUserTest(){

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
