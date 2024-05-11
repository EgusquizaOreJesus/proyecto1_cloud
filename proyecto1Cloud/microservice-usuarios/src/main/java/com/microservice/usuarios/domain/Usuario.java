package com.microservice.usuarios.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data       // Crea los getter y setter
@Builder    // Crea un objeto con todos los atributos
@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    // Atributos
    // name
    // atributo obligatorio
    @Column(nullable = false)
    private String nickname;
    // email

    private String email;

    // password
    private String password;

    private String image_path;

    private String background_picture;

    private String enlace_imagen;

    private String enlace_portada;

    @ElementCollection
    private Set<Long> hilosCreados = new HashSet<>();


    @ElementCollection
    private Set<Long> respuestasParticipadas = new HashSet<>();

    @ElementCollection
    private Set<Long> estados = new HashSet<>();

    @ElementCollection
    private List<Long> respuestas = new ArrayList<>();

}
