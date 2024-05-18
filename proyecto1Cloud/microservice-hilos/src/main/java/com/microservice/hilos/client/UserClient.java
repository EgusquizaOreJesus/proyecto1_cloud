package com.microservice.hilos.client;


import com.microservice.hilos.dto.HilosDTO;
import com.microservice.hilos.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(value = "msvc-usuarios")
public interface UserClient {

    // Lista de HilosDTO que se obtiene del microservicio de usuarios por Id
    @GetMapping("/find/{id}")
    UsuarioDTO findUserById(@PathVariable Long id);

    @PutMapping("/updateHilos/{userId}")
    void updateHilos(@PathVariable Long userId, @RequestBody Long hiloID);
}
