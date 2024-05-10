package com.microservice.usuarios.client;

import com.microservice.usuarios.dto.HilosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(value = "msvc-hilos", url = "http://localhost:9090/api/hilos")
public interface HilosClient {
    // Lista de HilosDTO que se obtiene del microservicio de usuarios por Id

    @GetMapping("/search_by_user/{userId}")
    List<HilosDTO> findAllHilosByUser(@PathVariable Long userId);
}
