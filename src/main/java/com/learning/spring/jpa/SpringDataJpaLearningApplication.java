package com.learning.spring.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaLearningApplication {

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaLearningApplication.class, args);
	}

}
