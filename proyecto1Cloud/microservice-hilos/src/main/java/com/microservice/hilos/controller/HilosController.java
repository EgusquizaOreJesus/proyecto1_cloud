package com.microservice.hilos.controller;


import com.microservice.hilos.domain.Hilos;
import com.microservice.hilos.dto.HilosDTO;
import com.microservice.hilos.service.HilosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hilos")
public class HilosController {

    @Autowired
    private HilosService hilosService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> saveHilos(@RequestBody HilosDTO hilosdto, @PathVariable Long userId) {
        return hilosService.save(hilosdto, userId);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findHilos(@PathVariable Long id) {
        System.out.println("Buscando hilo: " + id);
        return hilosService.findById(id);
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(hilosService.findAll());
    }

    @GetMapping("/search_by_user/{userId}")
    public ResponseEntity findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(hilosService.findByUserId(userId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHilos(@PathVariable Long id) {
        hilosService.deleteHilos(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update_respuestas/{hiloId}")
    public void updateRespuestas(@PathVariable Long hiloId, @RequestBody Long respuestaId) {
        hilosService.updateRespuestas(hiloId, respuestaId);
    }
}
