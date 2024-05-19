package com.microservice.respuesta.client;

import com.microservice.respuesta.dto.HilosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "msvc-hilos" , url = "http://${my_ip.value}/api/hilos")
public interface HilosClient {

    @GetMapping("/find/{id}")
    HilosDTO findHilosById(@PathVariable Long id);


    // actualizar la lista de mensajes de hilo
    @PutMapping("/update_respuestas/{hiloId}")
    void updateRespuestas(@PathVariable Long hiloId, @RequestBody Long respuestaId);
}
