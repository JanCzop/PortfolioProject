package com.example.portfolioproject.Login;

import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_not_found;
import com.example.portfolioproject.Exceptions.Exc_null_data;
import com.example.portfolioproject.Repositories.User_repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Login_service {

    private final User_repository user_repository;
    private final BCryptPasswordEncoder password_encoder;

    public Login_service(User_repository user_repository, BCryptPasswordEncoder password_encoder) {
        this.user_repository = user_repository;
        this.password_encoder = password_encoder;
    }

    public User login_user(Login_DTO login_dto){
        if(login_dto == null) throw new Exc_null_data("User is null");
        if(!login_dto.validate_DTO()) throw new IllegalArgumentException("There are missing arguments");
        User user = user_repository.findByUsername(login_dto.getUsername())
                .orElseThrow(() -> new Exc_entity_not_found("User with username " + login_dto.getUsername() + " does not exist!"));
        if (!password_encoder.matches(login_dto.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("Incorrect password!");
        else return user;
    }
}
