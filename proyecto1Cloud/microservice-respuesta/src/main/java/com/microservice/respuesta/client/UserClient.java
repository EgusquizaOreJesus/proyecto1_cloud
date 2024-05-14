package com.microservice.respuesta.client;


import com.microservice.respuesta.dto.ImagesDTO;
import com.microservice.respuesta.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(value = "msvc-usuarios", url = "http://54.89.113.172:9092/api/usuarios")
public interface UserClient {

    // Lista de HilosDTO que se obtiene del microservicio de usuarios por Id
    @GetMapping("/{usuario_id}/profile_picture_path")
    ImagesDTO getProfilePicturePath(@PathVariable Long usuario_id);

    @GetMapping("/find/{id}")
    UsuarioDTO findUserById(@PathVariable Long id);

    @PutMapping("/updateRespuestas/{userId}")
    void updateRespuestas(@PathVariable Long userId, @RequestBody Long respuestaId);
}
