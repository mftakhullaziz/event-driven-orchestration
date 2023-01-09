package com.service.swaggerUIDocsServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@EnableSwagger2
//@EnableSwagger2WebFlux
@EnableScheduling
@SpringBootApplication
public class SwaggerUIDocsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerUIDocsServerApplication.class, args);
	}

}
