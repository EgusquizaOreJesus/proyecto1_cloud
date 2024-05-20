package com.microservice.respuesta.persistence;

import com.microservice.respuesta.domain.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {


    List<Respuesta> findByHilo(Long hiloId);

    List<Respuesta> findByHiloAndRespuestaPadreIsNull(Long hiloId);

}
