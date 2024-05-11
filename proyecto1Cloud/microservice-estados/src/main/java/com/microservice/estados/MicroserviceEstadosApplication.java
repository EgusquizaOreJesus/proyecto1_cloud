package com.microservice.estados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MicroserviceEstadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEstadosApplication.class, args);
	}

}
