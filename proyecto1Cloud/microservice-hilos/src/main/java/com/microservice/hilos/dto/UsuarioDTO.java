package com.microservice.hilos.dto;

import jakarta.persistence.ElementCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsuarioDTO {
    private String nickname;
    // email

    private String email;

    // password
    private String password;

    private String image_path;

    private String background_picture;

    private String enlace_imagen;

    @ElementCollection
    private Set<Long> hilosCreados = new HashSet<>();

}
