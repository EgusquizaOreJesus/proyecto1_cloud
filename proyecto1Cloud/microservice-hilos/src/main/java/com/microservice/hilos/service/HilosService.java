package com.microservice.hilos.service;

import com.microservice.hilos.domain.Hilos;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HilosService {

    List<Hilos> findAll();

    Hilos findById(Long id);

    ResponseEntity<String> save(Hilos hilos);

    // todos los hilos que pertenecen a ese usuario
    List<Hilos> findByUserId(Long userId);
}
