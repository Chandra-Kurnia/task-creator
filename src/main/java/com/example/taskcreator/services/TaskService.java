package com.example.taskcreator.services;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.*;
import com.example.taskcreator.entities.Priority;
import com.example.taskcreator.entities.Status;
import com.example.taskcreator.entities.Task;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.repositories.PriorityRepository;
import com.example.taskcreator.repositories.StatusRepository;
import com.example.taskcreator.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        Priority priority = getSinglePriority(payload.getPriorityId());
//        get status
        Status status = getSingleStatus(payload.getStatusId());
//        set entity
        Task task = new Task(payload, status, priority);
//        save entity to db
        Task result = taskRepository.save(task);
//        return true if success return false if error
        if(result.getTaskId() == null) throw new TCException("Failed create new task!");
        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully create task",
                true,
                null,
                result
        ));
    }

    public ResponseEntity<MessageModel> getTask(Long id) throws TCException {
        Task task = getSingleTask(id);
        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully get task",
                true,
                null,
                task
        ));
    }

    public ResponseEntity<MessageModel> updateTask(Long id, TaskPayload payload) throws TCException {
        Task task = getSingleTask(id);
        Priority priority = getSinglePriority(payload.getPriorityId());
        Status status = getSingleStatus(payload.getStatusId());

        task.setTaskName(payload.getTaskName());
        task.setStatus(status);
        task.setPriority(priority);
        task.setDueDate(payload.getDueDate());

        Task result = taskRepository.save(task);

        if(result.getTaskId() == null) throw new TCException("Failed update task!");
        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully update task",
                true,
                null,
                result
        ));
    }

    public ResponseEntity<MessageModel> listTasks(TaskFilter filter) throws TCException {
        Pageable pageable = PageRequest.of(filter.getPage()-1, filter.getLimit());

        Page<Object[]> taskPage = taskRepository.getTasks(
                filter.getPriorityId(),
                filter.getStatusId(),
                filter.getTaskName(),
                pageable
        );
        List<Object[]> tasksObject = taskPage.getContent();
        List<ListOfTasks> tasks = new ArrayList<>();

        for(Object[] task : tasksObject) {
            tasks.add(new ListOfTasks(task));
        }

        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully get data task",
                true,
                new Pagination(taskPage.getTotalElements(), taskPage.getTotalPages(), taskPage.getNumber()+1),
                tasks
        ));
    }

    public ResponseEntity<MessageModel> sumALlNumber(ListOfNumberPayload num) throws TCException {
        List<Integer> listNum = num.getListOfNumber();

        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully sum all number",
                true,
                null,
                sumUsingStream(listNum)
        ));
    }

    public static int sumUsingStream(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public Task getSingleTask(Long id) throws TCException {
        Optional<Task> foundTask = taskRepository.findById(id);
        return foundTask.orElseThrow(() -> new TCException("Task not found!"));
    }

    public Priority getSinglePriority(Long id) throws TCException {
        Optional<Priority> foundPriority = priorityRepository.findById(id);
        return foundPriority.orElseThrow(() -> new TCException("Priority not found!"));
    }

    public Status getSingleStatus(Long id) throws TCException {
        Optional<Status> foundStatus = statusRepository.findById(id);
        return foundStatus.orElseThrow(()-> new TCException("Status not found!"));
    }
}
