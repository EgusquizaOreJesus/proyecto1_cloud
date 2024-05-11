package com.microservice.respuesta.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDTO {

    private Long id;
    private String contenido;
    private List<Long> subRespuestaIds = new ArrayList<>();

    private Long RespuestaPadreId;

    private Long HiloId;

    private Long UsuarioId;

    private String nickname;

    private String enlace_imagen;


    private int cantidadReacciones = 0;

    private Date fechaCreacion;
}
