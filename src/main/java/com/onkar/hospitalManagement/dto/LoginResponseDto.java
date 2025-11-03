package com.onkar.hospitalManagement.dto;

public class LoginResponseDto {

    String jwt;
    int userId;

    public LoginResponseDto(String jwt, int userId) {
        this.jwt = jwt;
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
