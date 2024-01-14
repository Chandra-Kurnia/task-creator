package com.example.taskcreator.repositories;

import com.example.taskcreator.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUserId(Long id);
}
