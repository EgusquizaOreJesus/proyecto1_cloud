package com.microservice.usuarios.dto;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nickname;
    private Long id;

    private String email;

    private String enlace_imagen;

    private String enlace_portada;


    private Set<Long> hilosCreados = new HashSet<>();

    private Set<Long> respuestasParticipadas = new HashSet<>();

    private Set<Long> estados = new HashSet<>();

    private List<Long> respuestas = new ArrayList<>();

}
