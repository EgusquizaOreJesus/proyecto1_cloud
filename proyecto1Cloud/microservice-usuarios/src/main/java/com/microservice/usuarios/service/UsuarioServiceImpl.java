package com.microservice.usuarios.service;

import com.microservice.usuarios.client.HilosClient;
import com.microservice.usuarios.domain.Usuario;
import com.microservice.usuarios.dto.HilosDTO;
import com.microservice.usuarios.http.response.HilosByUserResponse;
import com.microservice.usuarios.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HilosClient hilosClient;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void updateHilos(Long id, Long hilos) {
        Usuario user = usuarioRepository.findById(id).orElse(null);
        if (user != null) {
            user.getHilosCreados().add(hilos);
            usuarioRepository.save(user);
        }
    }

    @Override
    public void update(Long id, Usuario usuario) {
        Usuario user = usuarioRepository.findById(id).orElse(null);
        if (user != null) {
            user.setEmail(usuario.getEmail());
            user.setNickname(usuario.getNickname());
            user.getHilosCreados().addAll(usuario.getHilosCreados());
            usuarioRepository.save(user);
        }
    }

    @Override
    public void save(Usuario course) {
        usuarioRepository.save(course);
    }

    @Override
    public HilosByUserResponse findHilosByUser(Long userId) {
        // printear
        System.out.println("Buscando hilos por usuario: " + userId);
        // Consultar el usuario
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);
        // Consultar los hilos del usuario
        List<HilosDTO> hilosDTOList = hilosClient.findAllHilosByUser(userId);

        // Crear la respuesta personalizada
        return HilosByUserResponse.builder()
                .nickname(usuario.getNickname())
                .email(usuario.getEmail())
                .hilos(hilosDTOList).build();
    }
}
