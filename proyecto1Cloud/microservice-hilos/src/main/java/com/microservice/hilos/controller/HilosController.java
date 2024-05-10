package com.microservice.hilos.controller;


import com.microservice.hilos.domain.Hilos;
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

    @PostMapping("/create")
    public ResponseEntity<?> saveHilos(@RequestBody Hilos hilos) {
        return hilosService.save(hilos);
    }

    @GetMapping("/find/{id}")
    public Hilos findHilos(@PathVariable Long id) {
        return hilosService.findById(id);
    }

    @GetMapping("/all")
    public List<Hilos> findAll() {
        return hilosService.findAll();
    }

    @GetMapping("/search_by_user/{userId}")
    public ResponseEntity findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(hilosService.findByUserId(userId));
    }
}
