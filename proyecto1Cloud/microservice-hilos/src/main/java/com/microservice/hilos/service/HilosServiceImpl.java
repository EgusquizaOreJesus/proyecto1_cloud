package com.microservice.hilos.service;

import com.microservice.hilos.client.UserClient;
import com.microservice.hilos.domain.Hilos;
import com.microservice.hilos.dto.HilosDTO;
import com.microservice.hilos.dto.UsuarioDTO;
import com.microservice.hilos.exception.HiloNotFoundException;
import com.microservice.hilos.exception.UserNotFoundException;
import com.microservice.hilos.persistence.HilosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public ResponseEntity<HilosDTO> findById(Long id) {
        Hilos hilos = hilosRepository.findById(id).orElse(null);
        if (hilos != null) {
            return ResponseEntity.ok(HilosDTO.builder()
                    .tema(hilos.getTema())
                    .contenido(hilos.getContenido())
                    .fechaCreacion(hilos.getFechaCreacion())
                    .usuario(hilos.getUsuario())
                    .respuestas(hilos.getRespuestas())
                    .build()
            );
        }
        throw new HiloNotFoundException();

    }

    @Override
    public ResponseEntity<HilosDTO> save(HilosDTO hilosdto,Long userId) {
        // validar que el usuario exista
        System.out.println("Buscando usuario: " + userId);
        UsuarioDTO usuario = null;
        try {
            usuario = userClient.findUserById(userId);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
        System.out.println("Usuario encontrado: " + usuario.getNickname() + " " + usuario.getEmail() + " " + usuario.getId());
        Hilos hilo = Hilos.builder()
                .tema(hilosdto.getTema())
                .contenido(hilosdto.getContenido())
                .fechaCreacion(new Date())
                .usuario(userId)
                .build();
        // actualizamos los hilos del usuario

        hilosRepository.save(hilo);
        userClient.updateHilos(hilo.getUsuario(), hilo.getId());
        return ResponseEntity.ok(HilosDTO.builder()
                .tema(hilo.getTema())
                .contenido(hilo.getContenido())
                .fechaCreacion(hilo.getFechaCreacion())
                .usuario(userId)
                .respuestas(hilo.getRespuestas())
                .build()
        );
    }
/*
    @Override
    public ResponseEntity<String> save2(Hilos hilos) {
        // validar que el usuario exista
        System.out.println("Buscando usuario: " + hilos.getUsuario());
        UsuarioDTO usuario = null;
        try {
            usuario = userClient.findUserById(hilos.getUsuario());
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

        // actualizamos los hilos del usuario
        System.out.println("hilos:" + hilos.getId());
        hilosRepository.save(hilos);
        userClient.updateHilos(hilos.getUsuario(), hilos.getId());
        return ResponseEntity.ok("Hilo guardado exitosamente");
    }
 */


    @Override
    public List<Hilos> findByUserId(Long userId) {
        return hilosRepository.findHilosByUsuario(userId);
    }

    @Override
    public void updateRespuestas(Long hiloId, Long respuestaId) {
        Hilos hilo = hilosRepository.findById(hiloId).orElseThrow( () -> new HiloNotFoundException());
        if (hilo != null) {
            hilo.getRespuestas().add(respuestaId);
            hilosRepository.save(hilo);
        }
    }

    @Override
    public void deleteHilos(Long id) {
        hilosRepository.deleteById(id);
    }
}
