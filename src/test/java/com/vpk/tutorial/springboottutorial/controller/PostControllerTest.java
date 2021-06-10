package com.vpk.tutorial.springboottutorial.controller;

import com.vpk.tutorial.springboottutorial.model.Post;
import com.vpk.tutorial.springboottutorial.model.User;
import com.vpk.tutorial.springboottutorial.service.PostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static com.vpk.tutorial.springboottutorial.utility.Helper.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private User user1;
    private Post post1, post2;
    private List<Post> user1PostsList;

    @BeforeEach
    private void setUp() {
        user1 = new User(1, "Mathew", 23, null);
        post1 = new Post(1, "Weekend Getaway", null);
        post2 = new Post(2, "Trip Photos", null);
        user1PostsList = new ArrayList<>();
        user1PostsList.add(post1);
        user1PostsList.add(post2);
    }

    @AfterEach
    private void tearDown(){
        user1PostsList.clear();
        user1 = null;
        post1 = post2 = null;
    }

    @Test
    public void createPostTest() throws Exception {
        when(postService.createPost(anyInt(), any())).thenReturn(post1);
        mockMvc.perform(post("/user/1/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(post1)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(post1)))
                .andDo(MockMvcResultHandlers.print());
        verify(postService,times(1)).createPost(anyInt(),any());
    }

    @Test
    public void retrievePost() throws Exception {
        when(postService.retrievePost(user1.getUserId(), post1.getId())).thenReturn(post1);
        mockMvc.perform(get("/user/1/post/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(post1)))
                .andDo(MockMvcResultHandlers.print());
        verify(postService,times(1)).retrievePost(anyInt(),anyInt());
    }

    @Test
    public void retrieveAllPost() throws Exception {
        when(postService.retrieveAllPost(user1.getUserId())).thenReturn(user1PostsList);
        mockMvc.perform(get("/user/1/post").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(user1PostsList)))
                .andDo(MockMvcResultHandlers.print());
        verify(postService,times(1)).retrieveAllPost(user1.getUserId());
    }

}
