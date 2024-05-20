package com.microservice.respuesta.service;

import com.microservice.respuesta.client.HilosClient;
import com.microservice.respuesta.client.UserClient;
import com.microservice.respuesta.domain.Respuesta;
import com.microservice.respuesta.dto.HilosDTO;
import com.microservice.respuesta.dto.ImagesDTO;
import com.microservice.respuesta.dto.RespuestaDTO;
import com.microservice.respuesta.dto.UsuarioDTO;
import com.microservice.respuesta.exception.HiloNotFoundException;
import com.microservice.respuesta.exception.RespuestaNotFoundException;
import com.microservice.respuesta.exception.UserNotFoundException;
import com.microservice.respuesta.persistence.RespuestaRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService{

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private HilosClient hilosClient;



    @Override
    public List<RespuestaDTO> findAll() {
        // verifico que exista images en mi usuario

        List<RespuestaDTO> respuestas = respuestaRepository.findAll().stream().map(
                respuesta -> RespuestaDTO.builder()
                        .id(respuesta.getId())
                        .contenido(respuesta.getContenido())
                        .fechaCreacion(respuesta.getFechaCreacion())
                        .enlace_imagen(respuesta.getEnlace_imagen())
                        .RespuestaPadreId(respuesta.getRespuestaPadre() != null ? respuesta.getRespuestaPadre().getId() : null)
                        .HiloId(respuesta.getHilo())
                        .subRespuestaIds(respuesta.getSubrespuestas().stream()
                                .map(Respuesta::getId)
                                .collect(Collectors.toList()))
                        .UsuarioId(respuesta.getUsuario())
                        .nickname(userClient.findUserById(respuesta.getUsuario()).getNickname())
                        .enlace_imagen(userClient.getProfilePicturePath(respuesta.getUsuario()).getEnlace_imagen())
                        .build()).toList();
        return respuestas;
    }

    @Override
    public ResponseEntity<RespuestaDTO> save(RespuestaDTO respuestaDTO, Long idEmisor, Long hiloId) {
        // validar si existe el usuario
        System.out.println("Buscando usuario: " + idEmisor);
        System.out.println("Buscando hilo: " + hiloId);


        UsuarioDTO usuario = null;
        try {
            usuario = userClient.findUserById(idEmisor);

        } catch (Exception e) {
            throw new UserNotFoundException();
        }

        // validar si existe el hilo

        try {
            hilosClient.findHilosById(hiloId);
        } catch (Exception e) {
            throw new HiloNotFoundException();
        }
        //System.out.println("Usuario encontrado: " + usuario.getNickname());
        //System.out.println("Usuario imagen: " + usuario.getEnlace_imagen());
        Respuesta respuesta =new Respuesta();
        respuesta.setContenido(respuestaDTO.getContenido());
        respuesta.setFechaCreacion(new Date());
        respuesta.setHilo(hiloId);
        respuesta.setUsuario(idEmisor);
        respuesta.setNickname(usuario.getNickname());
        respuesta.setEnlace_imagen(respuestaDTO.getEnlace_imagen());
        respuesta.setSubrespuestas(new ArrayList<>());
        // guardar la respuesta

        if(usuario.getEnlace_imagen() != null){
            respuesta.setEnlace_imagen(usuario.getEnlace_imagen());
        }
        // validar si la respuesta tiene una respuesta padre
        if (respuestaDTO.getRespuestaPadreId() != null){
            System.out.println("Buscando respuesta padre: " + respuestaDTO.getRespuestaPadreId());
            Respuesta respuestaPadre = respuestaRepository.findById(respuestaDTO.getRespuestaPadreId()).orElseThrow( () -> new RespuestaNotFoundException());
            respuestaPadre.getSubrespuestas().add(respuesta);
            respuesta.setRespuestaPadre(respuestaPadre);
        }
        // guardar la respuesta
        respuestaRepository.save(respuesta);
        // Actualizar hilos del usuario
        hilosClient.updateRespuestas(hiloId, respuesta.getId());
        // Actualizar respuestas del usuario
        userClient.updateRespuestas(idEmisor, respuesta.getId());

        RespuestaDTO repuestadto = new RespuestaDTO();
        repuestadto.setId(respuesta.getId());
        repuestadto.setContenido(respuesta.getContenido());
        repuestadto.setFechaCreacion(respuesta.getFechaCreacion());
        repuestadto.setEnlace_imagen(respuesta.getEnlace_imagen());
        repuestadto.setHiloId(respuesta.getHilo());
        repuestadto.setNickname(respuesta.getNickname());
        if (respuesta.getRespuestaPadre() != null){
            System.out.println("Respuesta padre encontrada: " + respuesta.getRespuestaPadre().getId());
            repuestadto.setRespuestaPadreId(respuesta.getRespuestaPadre().getId());
        }
        repuestadto.setUsuarioId(idEmisor);
        repuestadto.setSubRespuestaIds(respuesta.getSubrespuestas().stream()
                .map(Respuesta::getId)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(repuestadto);

    }

    @Override
    public ResponseEntity<RespuestaDTO> findById(Long respuestaId) {
        // validar si existe la respuesta
        Respuesta respuesta = respuestaRepository.findById(respuestaId).orElseThrow( () -> new RespuestaNotFoundException());
        RespuestaDTO respuestaDTO = RespuestaDTO.builder()
                .id(respuesta.getId())
                .contenido(respuesta.getContenido())
                .fechaCreacion(respuesta.getFechaCreacion())
                .enlace_imagen(respuesta.getEnlace_imagen())
                .RespuestaPadreId(respuesta.getRespuestaPadre() != null ? respuesta.getRespuestaPadre().getId() : null)
                .HiloId(respuesta.getHilo())
                .subRespuestaIds(respuesta.getSubrespuestas().stream()
                        .map(Respuesta::getId)
                        .collect(Collectors.toList()))
                .UsuarioId(respuesta.getUsuario())
                .nickname(userClient.findUserById(respuesta.getUsuario()).getNickname())
                .enlace_imagen(userClient.getProfilePicturePath(respuesta.getUsuario()).getEnlace_imagen())
                .build();

        return ResponseEntity.ok(respuestaDTO);
    }

    @Override
    public List<RespuestaDTO> findByHiloId(Long hiloId) {
        // validar que existe el hilo
        try {
            hilosClient.findHilosById(hiloId);
        } catch (Exception e) {
            throw new HiloNotFoundException();
        }
        List<RespuestaDTO> respuestas = respuestaRepository.findByHilo(hiloId).stream().map(
                respuesta -> RespuestaDTO.builder()
                        .id(respuesta.getId())
                        .contenido(respuesta.getContenido())
                        .fechaCreacion(respuesta.getFechaCreacion())
                        .enlace_imagen(respuesta.getEnlace_imagen())
                        .RespuestaPadreId(respuesta.getRespuestaPadre() != null ? respuesta.getRespuestaPadre().getId() : null)
                        .HiloId(respuesta.getHilo())
                        .subRespuestaIds(respuesta.getSubrespuestas().stream()
                                .map(Respuesta::getId)
                                .collect(Collectors.toList()))
                        .UsuarioId(respuesta.getUsuario())
                        .nickname(userClient.findUserById(respuesta.getUsuario()).getNickname())
                        .enlace_imagen(userClient.getProfilePicturePath(respuesta.getUsuario()).getEnlace_imagen())
                        .build()).toList();
        return respuestas;
    }

    @Override
    public List<RespuestaDTO> findRootRespuestas(Long hiloId) {
        // validar que existe el hilo
        try {
            hilosClient.findHilosById(hiloId);
        } catch (Exception e) {
            throw new HiloNotFoundException();
        }
        // buscar las respuestas que no tienen respuesta padre de ese hiloId
        List<RespuestaDTO> respuestas = respuestaRepository.findByHiloAndRespuestaPadreIsNull(hiloId).stream().map(
                respuesta -> RespuestaDTO.builder()
                        .id(respuesta.getId())
                        .contenido(respuesta.getContenido())
                        .fechaCreacion(respuesta.getFechaCreacion())
                        .enlace_imagen(respuesta.getEnlace_imagen())
                        .RespuestaPadreId(respuesta.getRespuestaPadre() != null ? respuesta.getRespuestaPadre().getId() : null)
                        .HiloId(respuesta.getHilo())
                        .subRespuestaIds(respuesta.getSubrespuestas().stream()
                                .map(Respuesta::getId)
                                .collect(Collectors.toList()))
                        .UsuarioId(respuesta.getUsuario())
                        .nickname(userClient.findUserById(respuesta.getUsuario()).getNickname())
                        .enlace_imagen(userClient.getProfilePicturePath(respuesta.getUsuario()).getEnlace_imagen())
                        .build()).toList();
        return respuestas;
    }

    @Override
    public List<RespuestaDTO> findRespuestasHijas(Long respuestaPadreId) {
        // validar que existe la respuesta padre
        Respuesta respuestaPadre = respuestaRepository.findById(respuestaPadreId).orElseThrow( () -> new RespuestaNotFoundException());
        List<RespuestaDTO> respuestas = respuestaPadre.getSubrespuestas().stream().map(
                respuesta -> RespuestaDTO.builder()
                        .id(respuesta.getId())
                        .contenido(respuesta.getContenido())
                        .fechaCreacion(respuesta.getFechaCreacion())
                        .enlace_imagen(respuesta.getEnlace_imagen())
                        .RespuestaPadreId(respuesta.getRespuestaPadre() != null ? respuesta.getRespuestaPadre().getId() : null)
                        .HiloId(respuesta.getHilo())
                        .subRespuestaIds(respuesta.getSubrespuestas().stream()
                                .map(Respuesta::getId)
                                .collect(Collectors.toList()))
                        .UsuarioId(respuesta.getUsuario())
                        .nickname(userClient.findUserById(respuesta.getUsuario()).getNickname())
                        .enlace_imagen(userClient.getProfilePicturePath(respuesta.getUsuario()).getEnlace_imagen())
                        .build()).toList();
        return respuestas;
    }


}
