package com.microservice.estados.controller;


import com.microservice.estados.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {
    @Autowired
    EstadoService estadoService;







}
