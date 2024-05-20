package com.microservice.estados;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MicroserviceEstadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEstadosApplication.class, args);
	}
	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI().
				info(new Info()
						.title("Microservicio de Estados API")
						.version("1.0")
						.description("Documentación de la API de estados")
						.termsOfService("http://swagger.io/terms/")
						.license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("http://springdoc.org"))
				);
	}
}
