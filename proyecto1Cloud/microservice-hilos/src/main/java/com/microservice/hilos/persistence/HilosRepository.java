package com.microservice.hilos.persistence;

import com.microservice.hilos.domain.Hilos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HilosRepository extends JpaRepository<Hilos, Long> {

    // lista de hilos por id de usuario
    List<Hilos> findHilosByUsuario(Long usuarioId);

}
