package com.microservice.hilos.service;

import com.microservice.hilos.client.UserClient;
import com.microservice.hilos.domain.Hilos;
import com.microservice.hilos.dto.UsuarioDTO;
import com.microservice.hilos.persistence.HilosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HilosServiceImpl implements HilosService{

    @Autowired
    private HilosRepository hilosRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public List<Hilos> findAll() {
        return hilosRepository.findAll();
    }

    @Override
    public Hilos findById(Long id) {
        return hilosRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<String> save(Hilos hilos) {
        // validar que el usuario exista
        System.out.println("Buscando usuario: " + hilos.getUsuario());
        UsuarioDTO usuario = userClient.findUserById(hilos.getUsuario());
        if (usuario == null) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }
        // actualizamos los hilos del usuario
        System.out.println("hilos:" + hilos.getId());
        hilosRepository.save(hilos);


        userClient.updateHilos(hilos.getUsuario(), hilos.getId());
        return ResponseEntity.ok("Hilo guardado exitosamente");
    }

    @Override
    public List<Hilos> findByUserId(Long userId) {
        return hilosRepository.findHilosByUsuario(userId);
    }
}
