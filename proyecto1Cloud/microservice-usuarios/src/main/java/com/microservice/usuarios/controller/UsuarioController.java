package com.microservice.usuarios.controller;

import com.microservice.usuarios.domain.Usuario;
import com.microservice.usuarios.persistence.UsuarioRepository;
import com.microservice.usuarios.service.UsuarioService;
import org.hibernate.annotations.AnyKeyJavaClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity findUserById(@PathVariable Long id) {
        System.out.println("Buscando usuario con id: " + id);
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping("/create")
    public void saveUser(@RequestBody Usuario user) {
        usuarioService.save(user);
    }


    @GetMapping("/search_by_username/{userId}")
    public ResponseEntity findByUsername(@PathVariable Long userId) {
        return ResponseEntity.ok(usuarioService.findHilosByUser(userId));
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Usuario user) {
        usuarioService.update(id, user);
    }
    @PutMapping("/updateHilos/{userId}")
    public void updateHilos(@PathVariable Long userId, @RequestBody Long hilosCreados) {
        usuarioService.updateHilos(userId, hilosCreados);

    }
}
