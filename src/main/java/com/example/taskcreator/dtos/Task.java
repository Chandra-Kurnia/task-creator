package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class Task {
    private String taskId;
    private String taskName;
    private String status;
    private String priority;
    private LocalDate created_at;
    private LocalDate updated_at;
}
