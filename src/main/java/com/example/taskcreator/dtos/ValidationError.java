package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ValidationError {
    private String message;
    private List<String> errors;

    public ValidationError() {}
    public ValidationError(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
