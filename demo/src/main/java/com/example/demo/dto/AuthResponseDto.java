package com.example.demo.dto;

import com.example.demo.entity.ZenUser;

public class AuthResponseDto {

    private String token;
    private ZenUser user;

    public AuthResponseDto() {
    }

    public AuthResponseDto(String token, ZenUser user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ZenUser getUser() {
        return user;
    }

    public void setUser(ZenUser user) {
        this.user = user;
    }
}