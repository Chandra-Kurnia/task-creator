package com.example.taskcreator.services;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.StatusPayload;
import com.example.taskcreator.entities.Status;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.repositories.StatusRepository;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public ResponseEntity<MessageModel> createStatus(StatusPayload payload) throws TCException {
        Status status = new Status(payload.getStatusName());
        Status result = statusRepository.save(status);

        if(result.getStatusId() == null) throw new TCException("Failed create status!");

        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully create status",
                true,
                null,
                result
        ));
    }

    public ResponseEntity<MessageModel> getListStatus() throws TCException {
        List<Status> statusList = statusRepository.findAll();

        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully get list status",
                true,
                null,
                statusList
        ));
    }
}
