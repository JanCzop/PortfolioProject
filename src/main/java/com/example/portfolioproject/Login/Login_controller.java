package com.example.portfolioproject.Login;

import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_not_found;
import com.example.portfolioproject.Repositories.User_repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/login")
public class Login_controller {

    private final Login_service login_service;

    @Autowired
    public Login_controller(Login_service login_service) {
        this.login_service = login_service;
    }

    @PostMapping
    public ResponseEntity<String> login_user(@RequestBody Login_DTO login_dto){
        User user = login_service.login_user(login_dto);
        return new ResponseEntity<>("Successfully logged in user with ID: " + user.getId(), HttpStatus.OK);
    }

}
