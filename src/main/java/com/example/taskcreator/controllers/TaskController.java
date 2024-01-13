package com.example.taskcreator.controllers;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.TaskPayload;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/create-task")
    public ResponseEntity<MessageModel> createTask(@RequestBody TaskPayload payload) throws TCException {
        try {
            return taskService.createTask(payload);
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }

    @GetMapping(value = "/list-tasks")
    public ResponseEntity<MessageModel> getListTask() throws TCException {
        try {
            return taskService.listTask();
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }

    @PutMapping(value = "/update-task")
    public ResponseEntity<MessageModel> updateTask() throws TCException {
        try {
            return taskService.updateTask();
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }
}
