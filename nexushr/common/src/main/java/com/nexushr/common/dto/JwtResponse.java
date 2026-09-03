package com.nexushr.common.dto;

public class JwtResponse {

    private String token;
    private String refreshToken;
    private String role;

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public JwtResponse(String token, String refreshToken, String role) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}