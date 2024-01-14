package com.example.taskcreator.controllers;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.StatusPayload;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @PostMapping(value = "/create-status")
    public ResponseEntity<MessageModel> createStatus(@Valid @RequestBody StatusPayload payload) throws TCException {
        try {
            return statusService.createStatus(payload);
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }

    @GetMapping(value = "/list-status")
    public ResponseEntity<MessageModel> getListStatus() throws TCException {
        try {
            return statusService.getListStatus();
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }
}
