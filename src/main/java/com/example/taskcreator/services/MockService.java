package com.example.taskcreator.services;

import com.example.taskcreator.Exception.TCException;
import com.example.taskcreator.dtos.MockPayload;
import com.example.taskcreator.helpers.MessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class MockService {

    public ResponseEntity<MessageModel> mockAPI(MockPayload payload) throws TCException, IOException {
        URL url = new URL(payload.getUrlMock());
        HttpURLConnection httpRequest = (HttpURLConnection) url.openConnection();
        httpRequest.setRequestMethod(payload.getMethod());
        httpRequest.setRequestProperty("Content-type", "application/json");
        httpRequest.setDoOutput(true);

        int statusCode = httpRequest.getResponseCode();

        return ResponseEntity.status(statusCode).body(new MessageModel(
                "Succesfully get mock data API",
                true,
                null,
                parseJsonToNode(readResponse(httpRequest.getInputStream()))
        ));
    }

    private static String readResponse(InputStream inputStream) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    private static JsonNode parseJsonToNode(String json) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(json);
    }
}
