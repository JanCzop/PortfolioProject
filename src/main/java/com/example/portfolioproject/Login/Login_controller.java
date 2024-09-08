package com.example.portfolioproject.Login;

import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Repositories.User_repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/login")
public class Login_controller {

    private final User_repository user_repository;
    private final BCryptPasswordEncoder password_encoder;

    public Login_controller(User_repository user_repository, BCryptPasswordEncoder password_encoder) {
        this.user_repository = user_repository;
        this.password_encoder = password_encoder;
    }

    @PostMapping
    public ResponseEntity<String> login_user(@RequestBody Login_DTO login_dto){
        User user = user_repository.findByUsername(login_dto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User does not exist!"));
        if(password_encoder.matches(user.getPassword(),login_dto.getPassword()))
            return new ResponseEntity<>("Successfully logged user with ID: " + user.getId(),HttpStatus.OK);
        else throw  new IllegalArgumentException("User's password is incorrect!");
    }
}
