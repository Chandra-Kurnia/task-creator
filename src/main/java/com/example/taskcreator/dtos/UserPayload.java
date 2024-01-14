package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserPayload {
    @NotBlank(message = "Priority name is required!")
    private String username;

    @NotBlank(message = "Priority name is required!")
    private String password;
}
