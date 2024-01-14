package com.example.taskcreator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Data
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

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

    public User(Long userId, String username, String password, LocalDate created_at, LocalDate updated_at){}

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
