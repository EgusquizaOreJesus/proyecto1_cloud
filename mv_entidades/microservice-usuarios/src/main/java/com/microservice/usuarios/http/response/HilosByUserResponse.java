package com.microservice.usuarios.http.response;

import com.microservice.usuarios.dto.HilosDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HilosByUserResponse {

    private String nickname;
    // email

    private String email;

    // password
    private String password;

    private String enlace_imagen;
    private String enlace_portada;
    private List<HilosDTO> hilos;
}
