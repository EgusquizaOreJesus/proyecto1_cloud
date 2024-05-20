package com.microservice.respuesta;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceRespuestaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRespuestaApplication.class, args);
	}
	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI().
				info(new Info()
						.title("Microservicio de Respuestas API")
						.version("1.0")
						.description("Documentación de la API de respuestas")
						.termsOfService("http://swagger.io/terms/")
						.license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("http://springdoc.org"))
				);
	}
}
