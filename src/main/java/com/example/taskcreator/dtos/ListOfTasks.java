package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Setter
@Getter
public class ListOfTasks {
    private BigInteger taskId;
    private String taskName;
    private Date dueDate;
    private Status status;
    private Priority priority;

    @Setter
    @Getter
    public static class Status{
        BigInteger statusId;
        String statusName;

        public Status(BigInteger statusId, String statusName) {
            this.statusId = statusId;
            this.statusName = statusName;
        }
    }

    @Setter
    @Getter
    public static class Priority{
        BigInteger priorityId;
        String priorityName;

        public Priority(BigInteger priorityId, String priorityName) {
            this.priorityId = priorityId;
            this.priorityName = priorityName;
        }
    }

    public ListOfTasks(){}

    public ListOfTasks(Object[] objectDb){

        this.taskId = (BigInteger) objectDb[0];
        this.taskName = (String) objectDb[1];
        this.dueDate = (Date) objectDb[2];
        this.priority = new Priority((BigInteger) objectDb[3], (String) objectDb[4]);
        this.status = new Status((BigInteger) objectDb[5], (String) objectDb[6]);
    }
}
