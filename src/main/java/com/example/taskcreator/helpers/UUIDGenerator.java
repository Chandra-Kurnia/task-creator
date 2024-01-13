package com.example.taskcreator.helpers;

import java.util.UUID;

public class UUIDGenerator {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
