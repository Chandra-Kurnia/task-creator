package com.example.taskcreator.repositories;

import com.example.taskcreator.dtos.TaskPayload;
import com.example.taskcreator.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
