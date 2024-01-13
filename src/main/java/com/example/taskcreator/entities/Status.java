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
@Table(name = "status")
public class Status {

    @Id
    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "status_name")
    private String statusName;
}
