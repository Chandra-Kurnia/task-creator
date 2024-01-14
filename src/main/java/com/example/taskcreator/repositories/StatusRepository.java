package com.example.taskcreator.repositories;

import com.example.taskcreator.entities.Priority;
import com.example.taskcreator.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByStatusName(String priorityName);
}
