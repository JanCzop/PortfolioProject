package com.example.portfolioproject.Login_tests;

import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Login.Login_DTO;
import com.example.portfolioproject.Login.Login_controller;
import com.example.portfolioproject.Repositories.User_repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(Login_controller.class)
public class Login_tests {
    @Autowired
    private MockMvc mock_mvc;
    @Mock
    private User_repository user_repository;
    @Mock
    BCryptPasswordEncoder password_encoder;
    @MockBean
    private Login_controller login_controller;

    private static Login_DTO DEFAULT_DTO;
    private static User DEFAULT_USER;
    private static String url = "/users/login";
    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        DEFAULT_DTO = new Login_DTO("username","password");

        DEFAULT_USER = new User();
        DEFAULT_USER.setId(UUID.randomUUID());
        DEFAULT_USER.setUsername(DEFAULT_DTO.getUsername());
        DEFAULT_USER.setPassword(DEFAULT_DTO.getPassword());
                ;
    }

    @Test
    public void test_login_SUCCESS() throws Exception {
        Login_DTO dto = DEFAULT_DTO;
        User user = DEFAULT_USER;

        when(user_repository.findByUsername(any(String.class))).thenReturn(Optional.of(user));
        when(password_encoder.matches(any(String.class),any(String.class))).thenReturn(true);

        mock_mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully logged in user with ID: " + user.getId()));
    }

}
