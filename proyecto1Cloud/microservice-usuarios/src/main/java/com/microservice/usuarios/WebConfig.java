package com.microservice.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AppConfig appConfig;

    @Value("${frontend.base-url}")
    private String frontendBaseUrl;
    // Esto permite que el frontend se comunique con el backend sin problemas
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(frontendBaseUrl)
                .allowedMethods("GET", "POST", "PUT", "DELETE");
        // Puedes ajustar allowedMethods según tus necesidades
    }
}
