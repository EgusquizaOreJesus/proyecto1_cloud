package com.microservice.usuarios.controller;

import com.microservice.usuarios.domain.Usuario;
import com.microservice.usuarios.dto.ImagesDTO;
import com.microservice.usuarios.dto.UsuarioDTO;
import com.microservice.usuarios.exception.UserNotFoundException;
import com.microservice.usuarios.persistence.UsuarioRepository;
import com.microservice.usuarios.service.UsuarioService;
import org.hibernate.annotations.AnyKeyJavaClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;



    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping("/create")
    public void saveUser(@RequestBody Usuario user) {
        usuarioService.save(user);
    }


    // Listar todos los hilos de un usuario
    @GetMapping("/search_by_username/{userId}")
    public ResponseEntity findByUsername(@PathVariable Long userId) {
        return ResponseEntity.ok(usuarioService.findHilosByUser(userId));
    }

    // Listar todas las respuestas de un usuario
    /*
        @GetMapping("/{id}/respuestas-participadas")
    public ResponseEntity<List<Respuesta>> getResponsesParticipatedByUser(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUserById(id);
        if (usuario != null) {
            List<Respuesta> respuestas_usuario = respuestaService.getResponsesParticipatedByUser(usuario);
            return ResponseEntity.ok(respuestas_usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     */

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Usuario user) {
        usuarioService.update(id, user);
    }
    @PutMapping("/updateHilos/{userId}")
    public void updateHilos(@PathVariable Long userId, @RequestBody Long hilosCreados) {
        usuarioService.updateHilos(userId, hilosCreados);
    }

    @PutMapping("/updateRespuestas/{userId}")
    public void updateRespuestas(@PathVariable Long userId, @RequestBody Long respuestasCreadas) {
        usuarioService.updateRespuestas(userId, respuestasCreadas);
    }

    @PutMapping("/updateEstado/{userId}")
    public void updateEstado(@PathVariable Long userId, @RequestBody Long estado) {
        usuarioService.updateEstado(userId, estado);
    }
    @PostMapping("/{userId}/upload_picture")
    public ResponseEntity<?> uploadPicture(
            @PathVariable("userId") Long usuarioId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("pictureType") String pictureType){
        try {
            return ResponseEntity.ok(usuarioService.uploadPicture(usuarioId, file, pictureType));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen");
        }
    }


    @GetMapping("/{usuario_id}/profile_picture")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable("usuario_id") Long usuarioId) {
        return (usuarioService.getProfilePicture(usuarioId));
    }
    @GetMapping("/{usuario_id}/background_picture")
    public ResponseEntity<byte[]> getBackgroundPicture(@PathVariable("usuario_id") Long usuarioId) {
        return (usuarioService.getBackgroundPicture(usuarioId));
    }

    // get para botarme las rutas de las imagenes
    @GetMapping("/{usuario_id}/profile_picture_path")
    public ResponseEntity<ImagesDTO> getProfilePicturePath(@PathVariable("usuario_id") Long usuarioId) {
        return usuarioService.getProfilePicturePath(usuarioId);
    }


    // delete
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}
