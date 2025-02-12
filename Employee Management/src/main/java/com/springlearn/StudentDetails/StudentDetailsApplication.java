package com.springlearn.StudentDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StudentDetailsApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(StudentDetailsApplication.class, args);

//		ClassRepo repo = context.getBean(ClassRepo.class);

//		System.out.println(repo.findAll());
	}

}
