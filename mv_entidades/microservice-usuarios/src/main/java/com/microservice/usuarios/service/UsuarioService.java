package com.microservice.usuarios.service;

import com.microservice.usuarios.domain.Usuario;
import com.microservice.usuarios.dto.ImagesDTO;
import com.microservice.usuarios.dto.UsuarioDTO;
import com.microservice.usuarios.http.response.HilosByUserResponse;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UsuarioService {
    List<UsuarioDTO> findAll();

    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> findByEmail(String email);

    boolean existsUserByEmail(String email);

    boolean existUserByNickname(String nickname);

    ResponseEntity<?> findByNickname(String nickname);

    UserDetails findByNicknameDetails(String nickname);

    void updateHilos(Long id, Long hilos);

    void updateRespuestas(Long id, Long respuestas);

    void updateEstado(Long id, Long estado);
    ResponseEntity<?> uploadPicture(Long usuarioId, MultipartFile file, String pictureType) throws IOException;

    void update(Long id, Usuario usuario);

    ResponseEntity<byte[]> getProfilePicture(Long usuarioId);

    ResponseEntity<byte[]> getBackgroundPicture(Long usuarioId);


    ResponseEntity<ImagesDTO> getProfilePicturePath(Long usuarioId);

    void save(Usuario user);

    Usuario save_2(Usuario user);

    // todos los hilos que pertenecen a ese usuario
    HilosByUserResponse findHilosByUser(Long userId);

    UserDetailsService userDetailsService();
    // delete
    void delete(Long id);

}
