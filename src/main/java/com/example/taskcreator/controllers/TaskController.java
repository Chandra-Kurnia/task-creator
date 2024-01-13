package com.example.taskcreator.controllers;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.TaskFilter;
import com.example.taskcreator.dtos.TaskPayload;
import com.example.taskcreator.dtos.UpdateTaskPayload;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
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

    @GetMapping(value = "/get-task/{id}")
    public ResponseEntity<MessageModel> getTask(@PathVariable(value = "id") Long id) throws TCException {
        try {
            return taskService.getTask(id);
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }

    @PutMapping(value = "/update-task/{id}")
    public ResponseEntity<MessageModel> updateTask(
            @PathVariable(value = "id") Long id,
            @RequestBody TaskPayload payload
            ) throws TCException {
        try {
            return taskService.updateTask(id, payload);
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }

    @GetMapping(value = "/list-tasks")
    public ResponseEntity<MessageModel> getListTasks(@ModelAttribute TaskFilter filter) throws TCException {
        try {
            return taskService.listTasks(filter);
        }catch (TCException e) {
            throw new TCException(e.getMessage());
        }
    }
}
