package com.microservice.usuarios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${microservicio.base-url}")
    private String backendBaseUrl;



    public String getBackendBaseUrl(){
        return backendBaseUrl;
    }


}
