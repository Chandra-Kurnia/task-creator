package com.example.taskcreator.Exception;

import com.example.taskcreator.dtos.ValidationError;
import com.example.taskcreator.helpers.MessageModel;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

//    Custom Exception
    @ExceptionHandler(TCException.class)
    public ResponseEntity<MessageModel> handleCustomException(TCException e) {
        return ResponseEntity.badRequest().body(new MessageModel(e.getMessage(), false));
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
        System.out.println(e.getMessage());
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("message", "Internal Server Error!");
        return ResponseEntity.internalServerError().body(errorBody);
    }
}
