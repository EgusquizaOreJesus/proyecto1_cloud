package com.microservice.estados.service;

import com.microservice.estados.dto.EstadoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstadoService {

    List<EstadoDTO> findAll();

    EstadoDTO findById(Long id);

    List<EstadoDTO> findByUserId(Long userId);

    ResponseEntity<EstadoDTO> save(EstadoDTO estado, Long Userid);

    ResponseEntity delete(Long id);
}
