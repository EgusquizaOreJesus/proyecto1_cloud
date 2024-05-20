package com.microservice.estados.service;

import com.microservice.estados.client.UserClient;
import com.microservice.estados.domain.Estado;
import com.microservice.estados.dto.EstadoDTO;
import com.microservice.estados.dto.UsuarioDTO;
import com.microservice.estados.exception.EstadoNotFoundException;
import com.microservice.estados.exception.UserNotFoundException;
import com.microservice.estados.persistence.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService{

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private UserClient userClient;
    @Override
    public List<EstadoDTO> findAll() {
        List<EstadoDTO> estados = estadoRepository.findAll()
                .stream().map(estado -> EstadoDTO.builder()
                        .id(estado.getId())
                        .nickname(estado.getNickname())
                        .contenido(estado.getContenido())
                        .fechaCreacion(estado.getFechaCreacion())
                        .enlace_imagen(userClient.findUserById(estado.getUsuario()).getEnlace_imagen())
                        .cantidadReacciones(estado.getCantidadReacciones())
                        .isReport(estado.isReport())
                        .usuario(estado.getUsuario())
                        .build()
                ).toList();
      return estados;
    }

    @Override
    public EstadoDTO findById(Long id) {
        return estadoRepository.findById(id)
                .map(estado -> EstadoDTO.builder()
                        .id(estado.getId())
                        .nickname(estado.getNickname())
                        .contenido(estado.getContenido())
                        .fechaCreacion(estado.getFechaCreacion())
                        .enlace_imagen(userClient.findUserById(estado.getUsuario()).getEnlace_imagen())
                        .cantidadReacciones(estado.getCantidadReacciones())
                        .isReport(estado.isReport())
                        .usuario(estado.getUsuario())
                        .build()
                ).orElseThrow( () -> new EstadoNotFoundException());
    }

    @Override
    public List<EstadoDTO> findByUserId(Long userId) {
        // validar que el usuario exista
        try {
            userClient.findUserById(userId);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
        List<EstadoDTO> estados = estadoRepository.findByUsuario(userId)
                .stream().map(estado -> EstadoDTO.builder()
                        .id(estado.getId())
                        .nickname(userClient.findUserById(estado.getUsuario()).getNickname())
                        .contenido(estado.getContenido())
                        .fechaCreacion(estado.getFechaCreacion())
                        .enlace_imagen(userClient.findUserById(estado.getUsuario()).getEnlace_imagen())
                        .cantidadReacciones(estado.getCantidadReacciones())
                        .isReport(estado.isReport())
                        .usuario(estado.getUsuario())
                        .build()
                ).toList();
        return estados;
    }

    @Override
    public ResponseEntity<EstadoDTO> save(EstadoDTO estado, Long Userid) {
        //  validar que el usuario exista
        System.out.println("Buscando usuario: " + Userid);
        UsuarioDTO usuario = null;
        try {
            usuario = userClient.findUserById(Userid);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

        Estado estadoEntity = Estado.builder()
                .nickname(usuario.getNickname())
                .contenido(estado.getContenido())
                .fechaCreacion(new Date())
                .enlace_imagen(usuario.getEnlace_imagen())
                .usuario(Userid)
                .build();
        estadoRepository.save(estadoEntity);
        // actualizamos los estados del usuario
        userClient.updateEstado(Userid, estadoEntity.getId());


        EstadoDTO estadoDTO = EstadoDTO.builder()
                .id(estadoEntity.getId())
                .nickname(estadoEntity.getNickname())
                .contenido(estadoEntity.getContenido())
                .fechaCreacion(estadoEntity.getFechaCreacion())
                .enlace_imagen(estadoEntity.getEnlace_imagen())
                .cantidadReacciones(estadoEntity.getCantidadReacciones())
                .isReport(estadoEntity.isReport())
                .usuario(estadoEntity.getUsuario())
                .build();

        return ResponseEntity.ok(estadoDTO);
    }

    @Override
    public ResponseEntity delete(Long id) {
        estadoRepository.deleteById(id);
        return ResponseEntity.ok("Estado eliminado correctamente");
    }


}
