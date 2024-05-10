package com.microservice.estados.domain;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Data
@Builder
@Entity
@Table(name = "estado")
@AllArgsConstructor
@NoArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    private String nickname;

    @ElementCollection
    private List<String> contenido = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    private String user_profilepicture;

    private int cantidadReacciones;

    private boolean isReport;

    private Long usuario;


}
