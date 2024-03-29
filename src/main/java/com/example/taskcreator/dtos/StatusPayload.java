package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class StatusPayload {
    @NotBlank(message = "Status name is required!")
    private String statusName;
}
