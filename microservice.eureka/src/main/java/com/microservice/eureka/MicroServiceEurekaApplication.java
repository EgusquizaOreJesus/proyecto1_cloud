package com.microservice.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // Enable eureka server, ya que este es el servidor de eureka
public class MicroServiceEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceEurekaApplication.class, args);
	}

}
