package com.example.taskcreator.entities;

import com.example.taskcreator.dtos.TaskPayload;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Data
@Builder
@Table(name = "Task")
public class Task {

    @Id
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "due_date")
    private LocalDate dueDate;

//    @Column(name = "status")
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

//    @Column(name = "priority")
    @ManyToOne
    @JoinColumn(name = "priority_id", nullable = false)
    private Priority priority;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @CreationTimestamp
    @JsonIgnore
    private LocalDate created_at;

    @Column(
            name = "updated_at",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    @UpdateTimestamp
    @JsonIgnore
    private LocalDate updated_at;

    public Task(){}

    public Task(TaskPayload payload, Status status, Priority priority) {
        this.taskName = payload.getTaskName();
        this.status = status;
        this.priority = priority;
        this.dueDate = payload.getDueDate();
    }

    public Task(Long a, String b, LocalDate c, Status status, Priority priority, LocalDate d, LocalDate e){}
}
