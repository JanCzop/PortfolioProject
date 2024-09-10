package com.example.portfolioproject.Login_tests;

import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_not_found;
import com.example.portfolioproject.Exceptions.Exc_null_data;
import com.example.portfolioproject.Login.Login_DTO;
import com.example.portfolioproject.Login.Login_controller;
import com.example.portfolioproject.Login.Login_service;
import com.example.portfolioproject.Registration.Register_user_DTO;
import com.example.portfolioproject.Repositories.User_repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(Login_controller.class)
public class Login_controller_tests {

    @Autowired
    private MockMvc mock_mvc;
    @MockBean
    Login_service login_service;

    private static final String URL = "/users/login";
    private static Login_DTO DEFAULT_DTO;
    private static User DEFAULT_USER;
    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        DEFAULT_DTO = new Login_DTO("username","password");

        DEFAULT_USER = new User("username","email@example.com","password", User.Role.TEAM_MEMBER);
        DEFAULT_USER.setId(UUID.randomUUID());
    }

    @Test
    public void login_user_test_SUCCESS() throws Exception {
        Login_DTO dto = DEFAULT_DTO;
        when(login_service.login_user(any(Login_DTO.class))).thenReturn(DEFAULT_USER);

        mock_mvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(DEFAULT_DTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully logged in user with ID: " + DEFAULT_USER.getId()));
    }


    @Test
    public void login_user_test_FAILURE_wrong_username() throws Exception {
        Login_DTO dto = DEFAULT_DTO;
        dto.setUsername("Wrong_username");

        when(login_service.login_user(any(Login_DTO.class))).thenThrow(new Exc_entity_not_found("User not found"));

        mock_mvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("User not found"));
    }


    @Test
    public void login_user_test_FAILURE_wrong_password() throws Exception {
        when(login_service.login_user(any(Login_DTO.class)))
                .thenThrow(new IllegalArgumentException("Incorrect password!"));

        mock_mvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(DEFAULT_DTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Incorrect password!"));
    }

    @Test
    public void login_user_test_FAILURE_null_data() throws Exception {
        when(login_service.login_user(any(Login_DTO.class)))
                .thenThrow(new Exc_null_data("User is null"));

        mock_mvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().string("User is null"));
    }
}
