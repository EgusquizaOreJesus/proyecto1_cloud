package com.microservice.estados.client;


import com.microservice.estados.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "msvc-usuarios")
public interface UserClient {
    @GetMapping("/find/{id}")
    UsuarioDTO findUserById(@PathVariable Long id);

    @PutMapping("/updateEstado/{userId}")
    void updateEstado(@PathVariable Long userId, @RequestBody Long estado);
}
