package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PriorityPayload {

    @NotBlank(message = "Priority name is required!")
    private String priority;
}
