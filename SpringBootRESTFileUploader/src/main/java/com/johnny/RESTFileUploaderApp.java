package com.johnny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class RESTFileUploaderApp {
	
	public static void main(String[] args) {
		SpringApplication.run(RESTFileUploaderApp.class, args);
	}
	
}
