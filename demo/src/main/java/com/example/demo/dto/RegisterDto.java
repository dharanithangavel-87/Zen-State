package com.example.demo.dto;

import com.example.demo.entity.ZenUser;

public class RegisterDto {

    private String email;
    private String password;
    private String fullName;
    private ZenUser.UserRole role;

    public RegisterDto() {
    }

    public RegisterDto(String email, String password, String fullName, ZenUser.UserRole role) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ZenUser.UserRole getRole() {
        return role;
    }

    public void setRole(ZenUser.UserRole role) {
        this.role = role;
    }
}