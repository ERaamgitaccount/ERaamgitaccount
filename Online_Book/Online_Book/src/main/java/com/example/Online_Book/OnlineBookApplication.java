package com.example.Online_Book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class OnlineBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookApplication.class, args);
	}

}
