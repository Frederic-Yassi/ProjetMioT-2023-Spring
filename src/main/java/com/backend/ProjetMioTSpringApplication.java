package com.backend;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.backend.autres.*;

@SpringBootApplication

public class ProjetMioTSpringApplication {

	public static void main(String[] args) {

		//SpringApplication.run(ProjetMioTSpringApplication.class, args);
		SpringApplication app = new SpringApplication(ProjetMioTSpringApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
		app.run(args);
	}
}
