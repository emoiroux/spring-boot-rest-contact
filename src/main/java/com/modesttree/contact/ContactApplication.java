package com.modesttree.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ContactApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(ContactApplication.class, args);
	}

}
