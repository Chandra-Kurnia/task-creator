package com.example.taskcreator.controllers;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.MockPayload;
import com.example.taskcreator.helpers.MessageModel;
import com.example.taskcreator.services.MockService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/mock")
public class MockController {

    @Autowired
    MockService mockService;

    @PostMapping(value = "/test-mock-api")
    public ResponseEntity<MessageModel> testMockingAPI(@RequestBody MockPayload payload) throws TCException, IOException {
        try {
            return mockService.mockAPI(payload);
        }catch (TCException e){
            throw new TCException(e.getMessage());
        }catch (IOException e) {
            throw new IOException("as");
        }
    }
}
