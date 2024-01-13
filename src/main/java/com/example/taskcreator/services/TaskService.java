package com.example.taskcreator.services;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.TaskPayload;
import com.example.taskcreator.entities.Priority;
import com.example.taskcreator.entities.Status;
import com.example.taskcreator.entities.Task;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.repositories.PriorityRepository;
import com.example.taskcreator.repositories.StatusRepository;
import com.example.taskcreator.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    PriorityRepository priorityRepository;

    @Autowired
    StatusRepository statusRepository;

    public ResponseEntity<MessageModel> createTask(TaskPayload payload) throws TCException {
//        get priority
        Optional<Priority> foundPriority = priorityRepository.findById(payload.getPriorityId());
        Priority priority = foundPriority.orElseThrow(() -> new TCException("Priority not found!"));
//        get status
        Optional<Status> foundStatus = statusRepository.findById(payload.getStatusId());
        Status status = foundStatus.orElseThrow(()-> new TCException("Status not found!"));
//        set entity
        Task task = new Task(payload, status, priority);
//        save entity to db
        Task result = taskRepository.save(task);
//        return true if success return false if error
        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully create task",
                true,
                null,
                result
        ));
    }

    public ResponseEntity<MessageModel> listTask() throws TCException {
        return ResponseEntity.ok().body(new MessageModel());
    }

    public ResponseEntity<MessageModel> updateTask() throws TCException {
        return ResponseEntity.ok().body(new MessageModel());
    }
}
