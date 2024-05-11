package com.microservice.respuesta.service;

import com.microservice.respuesta.dto.RespuestaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RespuestaService {

    List<RespuestaDTO> findAll();

    ResponseEntity<RespuestaDTO> save(RespuestaDTO respuestaDTO, Long idEmisor, Long hiloId);

    ResponseEntity<RespuestaDTO> findById(Long respuestaId);

    List<RespuestaDTO> findByHiloId(Long hiloId);

    List<RespuestaDTO> findRootRespuestas(Long hiloId);

    List<RespuestaDTO> findRespuestasHijas(Long respuestaPadreId);
}
