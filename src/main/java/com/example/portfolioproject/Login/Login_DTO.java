package com.example.portfolioproject.Login;

import com.example.portfolioproject.Entities.Entity_DTO_validator;

public class Login_DTO implements Entity_DTO_validator {
    private String username;
    private String password;

    public Login_DTO() {
    }

    public Login_DTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login_DTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean validate_DTO() {
        return username != null && password != null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
