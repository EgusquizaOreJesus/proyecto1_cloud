package com.microservice.respuesta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImagesDTO {

    private String enlace_imagen;
    private String enlace_portada;
}
