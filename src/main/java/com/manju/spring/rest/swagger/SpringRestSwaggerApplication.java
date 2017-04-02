package com.manju.spring.rest.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.manju.spring.rest.swagger.config.AppConfig;

@SpringBootApplication
@Import(value = AppConfig.class)
public class SpringRestSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestSwaggerApplication.class, args);
	}
}
