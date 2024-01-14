package com.example.taskcreator.controllers;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.RefreshTokenPayload;
import com.example.taskcreator.dtos.UserPayload;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<MessageModel> register(
            @RequestBody UserPayload payload
            ) throws TCException {
        try {
            return userService.register(payload);
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<MessageModel> login(
            @RequestBody UserPayload payload
    ) throws TCException {
        try {
            return userService.login(payload);
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<MessageModel> refreshToken(
            @RequestBody RefreshTokenPayload refreshToken
            ) throws TCException {
        return userService.refreshToken(refreshToken);
    }
}
