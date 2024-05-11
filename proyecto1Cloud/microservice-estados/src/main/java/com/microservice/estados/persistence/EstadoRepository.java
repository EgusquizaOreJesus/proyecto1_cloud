package com.microservice.estados.persistence;

import com.microservice.estados.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

    List<Estado> findByUsuario(Long usuarioId);
}
