package com.microservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // Habilita el servidor de configuración
@SpringBootApplication
public class MicroServiceConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceConfigApplication.class, args);
	}

}
