package com.example.taskcreator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskcreatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskcreatorApplication.class, args);
		System.out.println("Listening spring boot application port:1234");
		System.out.println("Swagger access : http://localhost:1234/swagger-ui.html");
	}

}
