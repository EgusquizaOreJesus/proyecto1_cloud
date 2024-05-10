package com.microservice.usuarios.service;

import com.microservice.usuarios.domain.Usuario;
import com.microservice.usuarios.http.response.HilosByUserResponse;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Set;

public interface UsuarioService {
    List<Usuario> findAll();

    Usuario findById(Long id);

    void updateHilos(Long id, Long hilos);

    void update(Long id, Usuario usuario);
    void save(Usuario course);

    // todos los hilos que pertenecen a ese usuario
    HilosByUserResponse findHilosByUser(Long userId);



}
