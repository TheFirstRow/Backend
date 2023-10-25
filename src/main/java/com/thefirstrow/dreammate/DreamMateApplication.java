package com.thefirstrow.dreammate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DreamMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamMateApplication.class, args);
	}

}
