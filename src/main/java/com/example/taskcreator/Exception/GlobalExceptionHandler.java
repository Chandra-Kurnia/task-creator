package com.example.taskcreator.Exception;

import com.example.taskcreator.dtos.ValidationError;
import com.example.taskcreator.helpers.MessageModel;
import io.swagger.models.Response;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

//    Custom Exception
    @ExceptionHandler(TCException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(TCException e) {
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(errorBody);
    }

//    Validation Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidationException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(new ValidationError("Validation error", errors));
    }

//    Server Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleServerException(Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("message", "Internal Server Error!");
        return ResponseEntity.internalServerError().body(errorBody);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, String>> handleIOException(Exception e) {
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("message", "Failed get response from mock API, please check your URL and Method!");
        return ResponseEntity.badRequest().body(errorBody);
    }
}
