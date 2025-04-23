package com.example.applianceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ApplianceappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplianceappApplication.class, args);
	}

}
