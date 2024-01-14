package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefreshTokenPayload {
    private String refreshToken;
}
