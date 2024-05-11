package com.microservice.estados.controller;


import com.microservice.estados.dto.EstadoDTO;
import com.microservice.estados.service.EstadoService;
import jakarta.ws.rs.DELETE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;


    @GetMapping("/all")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(estadoService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity findById(Long id){
        return ResponseEntity.ok(estadoService.findById(id));
    }

    @GetMapping("/find_user/{Userid}")   // Listar todos los estados de un usuario
    public ResponseEntity findByUserId(@PathVariable Long Userid){
        return ResponseEntity.ok(estadoService.findByUserId(Userid));
    }

    @PostMapping("/save/{Userid}")
    public ResponseEntity save(@RequestBody EstadoDTO estado, @PathVariable Long Userid){
        return (estadoService.save(estado,Userid));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok(estadoService.delete(id));
    }




}
