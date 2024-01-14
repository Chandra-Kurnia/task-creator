package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UpdateTaskPayload {

    @NotBlank
    private String statusId;
}
