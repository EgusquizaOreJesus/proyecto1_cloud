package com.microservice.usuarios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${microservicio.base-url}")
    private String backendBaseUrl;

    @Value("${frontend.base-url}")
    private String frontendBaseUrl;

    public String getBackendBaseUrl() {
        return backendBaseUrl;
    }

    public String getFrontendBaseUrl() {
        return frontendBaseUrl;
    }
}
