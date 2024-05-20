package com.microservice.hilos;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MicroserviceHilosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceHilosApplication.class, args);
	}
	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI().
				info(new Info()
						.title("Microservicio de Hilos API")
						.version("1.0")
						.description("Documentación de la API de hilos")
						.termsOfService("http://swagger.io/terms/")
						.license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("http://springdoc.org"))
				);
	}
}
