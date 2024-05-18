package com.microservice.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableFeignClients		// Habilita el cliente feign
@EnableDiscoveryClient	// Habilita el cliente, ya que este es un cliente de eureka
@SpringBootApplication
public class MicroserviceUsuariosApplication {

	public static void main(String[] args) {
			SpringApplication.run(MicroserviceUsuariosApplication.class, args);
		// rutas publicas de usuarios

	}

}