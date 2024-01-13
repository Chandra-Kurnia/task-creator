package com.example.taskcreator.controllers;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.PriorityPayload;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.services.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PriorityController {

    @Autowired
    PriorityService priorityService;
    @PostMapping(value = "/create-priority")
    public ResponseEntity<MessageModel> createPriority(@RequestBody PriorityPayload payload) throws TCException {
        try {
            return priorityService.createPriority(payload);
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }
}
