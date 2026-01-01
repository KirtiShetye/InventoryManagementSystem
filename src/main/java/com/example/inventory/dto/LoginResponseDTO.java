package com.example.inventory.dto;

public class LoginResponseDTO {

    private final String accessToken;
    private final String tokenType = "Bearer";

    public LoginResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}