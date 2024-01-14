package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenResponse {
    private String accessToken;
    private String refreshToken;

    public TokenResponse(){}

    public TokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
