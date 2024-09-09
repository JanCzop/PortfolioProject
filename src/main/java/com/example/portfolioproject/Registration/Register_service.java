package com.example.portfolioproject.Registration;

import com.example.portfolioproject.Entities.User;
import com.example.portfolioproject.Exceptions.Exc_entity_already_exist;
import com.example.portfolioproject.Repositories.User_repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Register_service {

    private final User_repository user_repository;
    private final BCryptPasswordEncoder password_encoder;

    public Register_service(User_repository user_repository, BCryptPasswordEncoder password_encoder) {
        this.user_repository = user_repository;
        this.password_encoder = password_encoder;
    }

    public User register_user(Register_user_DTO register_dto){
        if (user_repository.findByUsername(register_dto.getUsername()).isPresent() ||
            user_repository.findByEmail(register_dto.getEmail()).isPresent())
            throw new Exc_entity_already_exist("User with this username or email already exists!");
        String hashed_password = password_encoder.encode(register_dto.getPassword());

        User user = new User(
                register_dto.getUsername(),
                register_dto.getEmail(),
                hashed_password,
                register_dto.getRole()
        );
        return user_repository.save(user);
    }
}
