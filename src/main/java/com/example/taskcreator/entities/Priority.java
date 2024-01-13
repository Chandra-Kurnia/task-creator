package com.example.taskcreator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "priority")
public class Priority {

    @Id
    @Column(name = "priority_id")
    private Long priorityId;

    @Column(name = "priority_name")
    private String priorityName;
}
