package com.microservice.estados.persistence;

import com.microservice.estados.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
