package com.test.marcopolo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {LoginController.class})
@WebMvcTest
public class LoginControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenValidLoginContext_thenSuccess() throws Exception {
        String loginContext = "{\"username\": \"marco\", \"password\" : \"polo\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType("application/json")
                .content(loginContext))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void whenUserNameEmpty_thenBadRequest() throws Exception {
        String loginContext = "{\"username\": \"\", \"password\" : \"polo\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType("application/json")
                .content(loginContext))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void whenPasswordEmpty_thenBadRequest() throws Exception {
        String loginContext = "{\"username\": \"marco\", \"password\" : \"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType("application/json")
                .content(loginContext))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void whenInvalidLoginContext_thenBadRequest() throws Exception {
        String loginContext = "{\"username\": \"test\", \"password\" : \"hello\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType("application/json")
                .content(loginContext))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("false"));
    }
}
