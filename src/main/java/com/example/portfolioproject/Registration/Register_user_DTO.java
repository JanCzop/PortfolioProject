package com.example.portfolioproject.Registration;

import com.example.portfolioproject.Entities.Entity_DTO_validator;
import com.example.portfolioproject.Entities.User;

import java.util.regex.Pattern;

public class Register_user_DTO implements Entity_DTO_validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{5,}$");

    private String username;
    private String email;
    private String password;
    private User.Role role;

    public Register_user_DTO() {
    }

    public Register_user_DTO(String username, String email, String password, User.Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Register_user_DTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean validate_DTO() {
        return validate_email() && validate_username() && validate_password();
    }

    private boolean validate_email() {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean validate_username() {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    private boolean validate_password() {
        return password != null && password.length() >= 5;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }
}
