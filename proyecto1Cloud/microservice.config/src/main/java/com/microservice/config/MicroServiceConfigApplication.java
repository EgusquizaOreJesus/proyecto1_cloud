package com.microservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // Habilita el servidor de configuración
public class MicroServiceConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceConfigApplication.class, args);
	}

}
