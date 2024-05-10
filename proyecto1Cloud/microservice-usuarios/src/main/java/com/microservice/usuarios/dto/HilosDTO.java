package com.microservice.usuarios.dto;

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
public class HilosDTO {

    @Size(max = 3000)
    @Column(nullable = false)
    private String tema;

    @Size(max = 3000)
    @Column(nullable = false)
    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false) // Configura el nombre de la columna
    private Date fechaCreacion;


    private Long usuario;

    //@ElementCollection
    //private List<Long> respuestas = new ArrayList<>();

    private Long cantidadReaccciones;

    private Long cantidadReports;
}
