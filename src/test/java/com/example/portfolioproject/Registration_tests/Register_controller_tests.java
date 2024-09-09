package com.example.portfolioproject.Registration_tests;

import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_already_exist;
import com.example.portfolioproject.Exceptions.Exc_null_data;
import com.example.portfolioproject.Registration.Register_controller;
import com.example.portfolioproject.Registration.Register_service;
import com.example.portfolioproject.Registration.Register_user_DTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(Register_controller.class)
public class Register_controller_tests {
    @Autowired
    private MockMvc mock_mvc;
    @MockBean
    Register_service register_service;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_register_user_SUCCESS() throws Exception {
        Register_user_DTO dto = new Register_user_DTO("username","email@example.com","password", User.Role.TEAM_MEMBER);
        User user = new User(dto.getUsername(), dto.getEmail(), "encoded_password",dto.getRole());

        when(register_service.register_user(any(Register_user_DTO.class))).thenReturn(user);

        mock_mvc.perform(MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"username\",\"email\":\"email@example.com\",\"password\":\"password\",\"role\":\"TEAM_MEMBER\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Successfully registered user with ID: " + user.getId()));
    }

    @Test
    public void test_register_user_FAILURE_duplicate_data() throws Exception {
        when(register_service.register_user(any(Register_user_DTO.class)))
                .thenThrow(new Exc_entity_already_exist("User with this username or email already exists!"));

        mock_mvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"username\",\"email\":\"email@example.com\",\"password\":\"password\",\"role\":\"TEAM_MEMBER\"}"))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("User with this username or email already exists!"));
    }

    @Test
    public void test_register_user_FAILURE_invalid_data() throws Exception {
        when(register_service.register_user(any(Register_user_DTO.class)))
                .thenThrow(new IllegalArgumentException("There are invalid arguments!"));

        mock_mvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"username\",\"email\":\"emailexamplecom123\",\"password\":\"password\",\"role\":\"TEAM_MEMBER\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("There are invalid arguments!"));
    }

    @Test
    public void test_register_user_FAILURE_null_fields_argument() throws Exception {
        when(register_service.register_user(any(Register_user_DTO.class)))
                .thenThrow(new IllegalArgumentException("There are invalid arguments!"));

        mock_mvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":null,\"email\":null,\"password\":null,\"role\":null}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("There are invalid arguments!"));
    }

    @Test
    public void test_register_user_FAILURE_null_object() throws Exception {
        when(register_service.register_user(any(Register_user_DTO.class)))
                .thenThrow(new Exc_null_data("User is null!"));

        mock_mvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().string("User is null!"));
    }


}
