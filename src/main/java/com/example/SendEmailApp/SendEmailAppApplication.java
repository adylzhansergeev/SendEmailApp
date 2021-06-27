package com.example.SendEmailApp;

import com.example.SendEmailApp.models.dtos.UserDto;
import com.example.SendEmailApp.models.entities.User;
import com.example.SendEmailApp.models.mappers.UserMapper;
import com.example.SendEmailApp.shared.utils.mappers.SuperMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SendEmailAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendEmailAppApplication.class, args);
	}
}
