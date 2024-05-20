package com.microservice.respuesta.controller;

import com.microservice.respuesta.dto.RespuestaDTO;
import com.microservice.respuesta.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @GetMapping("/all")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(respuestaService.findAll());
    }
    @PostMapping("/{idEmisor}/{hiloId}")   //genero una respuesta al hilo con id --> hiloId
    public ResponseEntity createRespuesta(@RequestBody RespuestaDTO respuestaDTO, @PathVariable Long idEmisor, @PathVariable Long hiloId){
        return respuestaService.save(respuestaDTO, idEmisor, hiloId);
    }

    @GetMapping("/{respuestaId}")
    public ResponseEntity findRespuestaById(@PathVariable Long respuestaId){
        return respuestaService.findById(respuestaId);
    }

    @GetMapping("/search_by_hilo/{hiloId}")     // busco todas las respuestas de un hilo
    public ResponseEntity findByHiloId(@PathVariable Long hiloId){
        return ResponseEntity.ok(respuestaService.findByHiloId(hiloId));
    }

    @GetMapping("/rootRespuestas/{hiloId}")     // todas las respuestas que no tienen respuesta padre
    public ResponseEntity findRootRespuestas(@PathVariable Long hiloId){
        return ResponseEntity.ok(respuestaService.findRootRespuestas(hiloId));
    }

    @GetMapping("respuestas_hijas/{respuestaPadreId}")   // todas las respuestas que tienen una respuesta padre
    public ResponseEntity findRespuestasHijas(@PathVariable Long respuestaPadreId){
        return ResponseEntity.ok(respuestaService.findRespuestasHijas(respuestaPadreId));
    }
}
