package com.example.convertobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ConvertoBackendApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ConvertoBackendApplication.class, args);
	}

}
