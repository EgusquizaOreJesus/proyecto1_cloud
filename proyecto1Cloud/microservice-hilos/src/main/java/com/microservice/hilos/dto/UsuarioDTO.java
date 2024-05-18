package com.microservice.hilos.dto;

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
    // email

    private String email;

    // password
    private String password;

    private String image_path;

    private String background_picture;

    private String enlace_imagen;

    private Long id;

    @ElementCollection
    private Set<Long> hilosCreados = new HashSet<>();

}
