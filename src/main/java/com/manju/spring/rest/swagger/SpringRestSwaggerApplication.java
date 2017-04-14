package com.manju.spring.rest.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.manju.spring.rest.swagger.config.AppConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Import(value = AppConfig.class)
@EnableGlobalMethodSecurity
@EnableSwagger2
public class SpringRestSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestSwaggerApplication.class, args);
	}
}
