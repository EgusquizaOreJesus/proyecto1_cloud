package com.microservice.estados.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class EstadoDTO {

    private Long id;
    private String nickname;

    private List<String> contenido = new ArrayList<>();

    private Date fechaCreacion;

    private String enlace_imagen;

    private int cantidadReacciones;

    private boolean isReport;

    private Long usuario;
}
