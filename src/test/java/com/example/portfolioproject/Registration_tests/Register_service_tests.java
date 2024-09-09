package com.example.portfolioproject.Registration_tests;

import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_already_exist;
import com.example.portfolioproject.Exceptions.Exc_null_data;
import com.example.portfolioproject.Registration.Register_service;
import com.example.portfolioproject.Registration.Register_user_DTO;
import com.example.portfolioproject.Repositories.User_repository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class Register_service_tests {
    @Mock
    private User_repository user_repository;
    @Mock
    private BCryptPasswordEncoder password_encoder;
    @InjectMocks
    private Register_service register_service;

    public Register_service_tests(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void register_user_SUCCESS(){
        Register_user_DTO dto = new Register_user_DTO("username","email@example.com",
                "password", User.Role.TEAM_MEMBER);
        User user = new User(dto.getUsername(), dto.getEmail(), "encoded_password", dto.getRole());

        when(password_encoder.encode(dto.getPassword())).thenReturn("encoded_password");
        when(user_repository.save(any(User.class))).thenReturn(user);

        User result = register_service.register_user(dto);

        assertEquals("username", result.getUsername());
        assertEquals("email@example.com", result.getEmail());
        assertEquals("encoded_password", result.getPassword());
        assertEquals(User.Role.TEAM_MEMBER, result.getRole());
    }
    @Test
    public void register_user_FAILURE_duplicate_data(){
        Register_user_DTO dto = new Register_user_DTO("username","email@example.com",
                "password", User.Role.TEAM_MEMBER);
        when(user_repository.findByEmail(dto.getEmail())).thenReturn(Optional.of(new User()));

        Exception result = null;
        try{
            register_service.register_user(dto);
        } catch (Exception e){
            result = e;
        }
        assertNotNull(result);
        assertTrue(result instanceof Exc_entity_already_exist);
    }

    @Test
    public void test_register_user_FAILURE_invalid_data(){
        Register_user_DTO dto = new Register_user_DTO("username","emailexamplecom123",
                "password", User.Role.TEAM_MEMBER);
        Exception result = null;
        try{
            register_service.register_user(dto);
        } catch (Exception e){
            result = e;
        }
        assertNotNull(result);
        assertTrue(result instanceof IllegalArgumentException);
    }
    @Test
    public void test_register_user_FAILURE_null_fields_argument(){
        Register_user_DTO dto = new Register_user_DTO(null,null,null, null);
        Exception result = null;
        try{
            register_service.register_user(dto);
        } catch (Exception e){
            result = e;
        }
        assertNotNull(result);
        assertTrue(result instanceof IllegalArgumentException);
    }
    @Test
    public void test_register_user_FAILURE_null_object(){
        Register_user_DTO dto = null;
        Exception result = null;
        try{
            register_service.register_user(dto);
        } catch (Exception e){
            result = e;
        }
        assertNotNull(result);
        assertTrue(result instanceof Exc_null_data);
    }
}
