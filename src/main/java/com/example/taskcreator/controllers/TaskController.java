package com.example.taskcreator.controllers;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.Task;
import com.example.taskcreator.helpers.MessageModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TaskController {

    @PostMapping(value = "/create-task")
    public ResponseEntity<MessageModel> createTask(@RequestBody Task payload) throws TCException {
            throw new TCException("Failed create new task");
    }

    @GetMapping(value = "/list-tasks")
    public ResponseEntity<MessageModel> getListTask() throws TCException {
        throw new TCException("Failed get task");
    }

    @PutMapping(value = "/update-task")
    public ResponseEntity<MessageModel> updateTask() throws TCException {
        throw new TCException("Failed update task");
    }
}
