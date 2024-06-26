package com.microservice.usuarios.seguridad.controllers;

import com.microservice.usuarios.seguridad.domain.ResponseDTO;
import com.microservice.usuarios.seguridad.dto.SignUpRequest;
import com.microservice.usuarios.seguridad.dto.SigninRequest;
import com.microservice.usuarios.seguridad.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://frontend002.s3-website-us-east-1.amazonaws.com")
public class AuthController {
    private final AuthenticationService authenticationService;



    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // cors
    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(@RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDTO>  signin(@RequestBody @Valid SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}

