package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TaskFilter {
    private int page = 1;
    private int limit = 10;
    private List<Long> priorityId;
    private List<Long> statusId;
    private String taskName;
}
