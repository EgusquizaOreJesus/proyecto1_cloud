package com.microservice.usuarios.seguridad.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Long id;
    private String token;
    private String nickName;
    private String email;

    private String enlace_imagen;
    private String enlace_portada;



}
