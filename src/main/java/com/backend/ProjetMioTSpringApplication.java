package com.backend;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class ProjetMioTSpringApplication {
	static public String env="" ;
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ProjetMioTSpringApplication.class);
		if(env=="docker"){
			app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
		}
		else {
			app.setDefaultProperties(Collections.singletonMap("server.port", "8084"));
		}
		app.run(args);
	}
}
