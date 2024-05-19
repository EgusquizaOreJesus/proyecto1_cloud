package com.microservice.hilos.domain;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data       // Crea los getter y setter
@Builder    // Crea un objeto con todos los atributos
@Entity
@Table(name = "hilos")
@AllArgsConstructor
@NoArgsConstructor
public class Hilos {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Size(max = 3000)
        @Column(nullable = false)
        private String tema;

        @Size(max = 3000)
        @Column(nullable = false)
        private String contenido;

        // generar automaticamente la fecha
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "fecha_creacion" ) // Configura el nombre de la columna
        private Date fechaCreacion;


        private Long usuario;

        private String enlace_imagen;

        @ElementCollection
        private List<Long> respuestas = new ArrayList<>();

        private Long cantidadReaccciones;

        private Long cantidadReports;



}
