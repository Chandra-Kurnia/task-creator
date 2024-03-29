package com.example.taskcreator.helpers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageModel {
    private String message;
    private boolean status;
    private Object pagination;
    private Object data;

    public MessageModel(){}

    public MessageModel(String message, Boolean status){
        this.message = message;
        this.status = status;
    }

    public MessageModel(String message, Boolean status, Object pagination, Object data) {
        this.message = message;
        this.status = status;
        this.pagination = pagination;
        this.data = data;
    }
}
