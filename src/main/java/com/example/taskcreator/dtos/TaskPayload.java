package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class TaskPayload {
    private Long priorityId;
    private Long statusId;
    private String taskName;
    private LocalDate dueDate;
}
