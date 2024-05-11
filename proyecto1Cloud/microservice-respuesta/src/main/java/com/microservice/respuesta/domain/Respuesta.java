package com.microservice.respuesta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data       // Crea los getter y setter
@Builder    // Crea un objeto con todos los atributos
@Entity
@Table(name = "respuesta")
@AllArgsConstructor
@NoArgsConstructor
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_report", nullable = false)
    private Long isReport = 0L;

    @Size(max = 3000)
    @Column(nullable = false)
    private String contenido;

    @Column(name = "cantidad_reacciones", nullable = false)
    private int cantidadReacciones=0;

    private Long hilo;


    @ManyToOne
    @JoinColumn(name = "respuesta_padre_id") // Relación de autoreferencia@
    @JsonIgnore
    private Respuesta respuestaPadre;

    @OneToMany(mappedBy = "respuestaPadre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> subrespuestas = new ArrayList<>();

    private Long usuario;

    private String nickname;
    @ElementCollection
    private Set<Long> usuariosParticipantes = new HashSet<>();

    private String enlace_imagen;

    private Date fechaCreacion;

}
