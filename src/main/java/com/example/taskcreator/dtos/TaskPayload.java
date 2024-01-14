package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Optional;

@Setter
@Getter
public class TaskPayload {

    @NotNull(message = "Priority id is required!")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Value must be number!")
    @Min(value = 1, message = "Priority id not valid!")
    private Long priorityId;

    @NotNull(message = "Status id is required!")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Value must be number!")
    @Min(value = 1, message = "Status id not valid!")
    private Long statusId;

    @NotBlank(message = "Task name is required!")
    private String taskName;

    @NotNull(message = "Due date is required!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "The date must be after today!")
    private LocalDate dueDate;
}
