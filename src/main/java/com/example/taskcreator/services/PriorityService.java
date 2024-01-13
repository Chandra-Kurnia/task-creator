package com.example.taskcreator.services;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.PriorityPayload;
import com.example.taskcreator.entities.Priority;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.repositories.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {

    @Autowired
    PriorityRepository priorityRepository;

    public ResponseEntity<MessageModel> createPriority(PriorityPayload payload) throws TCException {
        Priority priority = new Priority(payload.getPriority());
        Priority result = priorityRepository.save(priority);

        if(result.getPriorityId() == null) throw new TCException("Failed create priority!");

        return ResponseEntity.ok().body(new MessageModel(
                "Succesfully create status",
                true,
                null,
                result
        ));

    }
}