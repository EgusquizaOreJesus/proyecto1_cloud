package com.microservice.hilos.service;

import com.microservice.hilos.domain.Hilos;
import com.microservice.hilos.dto.HilosDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HilosService {

    List<Hilos> findAll();

    ResponseEntity<HilosDTO> findById(Long id);

    ResponseEntity<HilosDTO> save(HilosDTO hilosdto, Long userId);

    // todos los hilos que pertenecen a ese usuario
    List<Hilos> findByUserId(Long userId);


    void updateRespuestas(Long hiloId, Long respuestaId);

    void deleteHilos(Long id);
}
