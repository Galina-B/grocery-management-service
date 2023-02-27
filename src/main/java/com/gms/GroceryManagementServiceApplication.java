package com.gms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GroceryManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryManagementServiceApplication.class, args);
		logger.info("Grocery Management Service is running.");
	}

}
