package com.microservice.usuarios.seguridad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequest {
    private String email;
    private String password;

    private String nickname;

    private String enlace_imagen;
    private String enlace_portada;

    private Long id;


}
