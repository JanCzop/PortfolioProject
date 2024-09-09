package com.example.portfolioproject.Login;

import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_not_found;
import com.example.portfolioproject.Repositories.User_repository;
import jakarta.persistence.EntityNotFoundException;
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

    private final User_repository user_repository;
    private final BCryptPasswordEncoder password_encoder;

    public Login_controller(User_repository user_repository, BCryptPasswordEncoder password_encoder) {
        this.user_repository = user_repository;
        this.password_encoder = password_encoder;
    }

    @PostMapping
    public ResponseEntity<String> login_user(@RequestBody Login_DTO login_dto){
        if(!login_dto.validate_DTO()) throw new IllegalArgumentException("There are missing arguments");
        User user = user_repository.findByUsername(login_dto.getUsername())
                .orElseThrow(() -> new Exc_entity_not_found("User with username " + login_dto.getUsername() + " does not exist!"));
        if (!password_encoder.matches(login_dto.getPassword(), user.getPassword()))
            return new ResponseEntity<>("Incorrect password!", HttpStatus.UNAUTHORIZED);
        else return new ResponseEntity<>("Successfully logged in user with ID: " + user.getId(), HttpStatus.OK);
    }

}
