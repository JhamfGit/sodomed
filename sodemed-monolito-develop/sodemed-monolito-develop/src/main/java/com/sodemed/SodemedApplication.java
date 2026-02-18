package com.sodemed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SodemedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SodemedApplication.class, args);
	}

}
