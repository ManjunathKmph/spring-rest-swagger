package com.manju.spring.rest.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.manju.spring.rest.swagger.services.EmployeeService;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan(basePackages = "com.manju.spring.rest")
@EnableSwagger2
public class AppConfig {
	
	@Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("employees")
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build();
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Sample with Swagger")
                .description("Spring REST Sample with Swagger")
                .version("1.0")
                .build();
    }
	@Bean
	public EmployeeService getEmployeeService() {
		return new EmployeeService();
	}

}
