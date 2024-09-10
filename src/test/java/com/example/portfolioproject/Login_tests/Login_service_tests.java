package com.example.portfolioproject.Login_tests;

import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_not_found;
import com.example.portfolioproject.Exceptions.Exc_null_data;
import com.example.portfolioproject.Login.Login_DTO;
import com.example.portfolioproject.Login.Login_service;
import com.example.portfolioproject.Repositories.User_repository;
import com.example.portfolioproject.Test_config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringJUnitConfig(Test_config.class)
public class Login_service_tests {
    @Mock
    private User_repository user_repository;
    @Mock
    private BCryptPasswordEncoder password_encoder;
    @InjectMocks
    private Login_service login_service;

    private static Login_DTO DEFAULT_DTO;
    private static User DEFAULT_USER;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

       DEFAULT_DTO = new Login_DTO("username","password");

       DEFAULT_USER = new User("username","email@example.com","password", User.Role.TEAM_MEMBER);
       DEFAULT_USER.setId(UUID.randomUUID());
    }

    @Test
    public void login_user_test_SUCCESS(){
        when(user_repository.findByUsername(any(String.class))).thenReturn(Optional.of(DEFAULT_USER));
        when(password_encoder.matches(any(String.class),any(String.class))).thenReturn(true);

        User user = login_service.login_user(DEFAULT_DTO);

        assertEquals(user,DEFAULT_USER);
    }

    @Test
    public void login_user_test_FAILURE_wrong_username(){
        when(user_repository.findByUsername(any(String.class))).thenReturn(Optional.empty());
        when(password_encoder.matches(any(String.class),any(String.class))).thenReturn(true);

        assertThrows(Exc_entity_not_found.class, () -> login_service.login_user(DEFAULT_DTO));
    }

    @Test
    public void login_user_test_FAILURE_wrong_password(){
        when(user_repository.findByUsername(any(String.class))).thenReturn(Optional.of(DEFAULT_USER));
        when(password_encoder.matches(any(String.class),any(String.class))).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> login_service.login_user(DEFAULT_DTO));
    }

    @Test void login_user_test_FAILURE_null_data(){
        when(user_repository.findByUsername(any(String.class))).thenReturn(Optional.empty());
        when(password_encoder.matches(any(String.class),any(String.class))).thenReturn(false);

        assertThrows(Exc_null_data.class, () -> login_service.login_user(null));
    }


}
