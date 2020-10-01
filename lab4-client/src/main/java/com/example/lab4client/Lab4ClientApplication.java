package com.example.lab4client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class Lab4ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab4ClientApplication.class, args);
	}

}
