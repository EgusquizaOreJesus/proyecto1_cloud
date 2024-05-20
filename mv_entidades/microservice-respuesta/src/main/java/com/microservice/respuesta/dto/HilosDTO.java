package com.microservice.respuesta.dto;

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
public class HilosDTO {


    private String tema;

    private String contenido;

    private Date fechaCreacion;


    private Long usuario;


    private List<Long> respuestas = new ArrayList<>();

    private Long cantidadReaccciones;

    private Long cantidadReports;
}
