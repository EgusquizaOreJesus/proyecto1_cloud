package com.microservice.usuarios.seguridad.service;

import com.microservice.usuarios.domain.Role;
import com.microservice.usuarios.domain.Usuario;
import com.microservice.usuarios.exception.EmailAlreadyExitsException;
import com.microservice.usuarios.exception.EmailPasswordException;
import com.microservice.usuarios.exception.UserAlreadyExistsException;
import com.microservice.usuarios.persistence.UsuarioRepository;
import com.microservice.usuarios.seguridad.domain.ResponseDTO;
import com.microservice.usuarios.seguridad.dto.JwtAuthenticationResponse;
import com.microservice.usuarios.seguridad.dto.SignUpRequest;
import com.microservice.usuarios.seguridad.dto.SigninRequest;
import com.microservice.usuarios.seguridad.service.JwtService;
import com.microservice.usuarios.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class AuthenticationService {
    private final UsuarioRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;

    @Autowired
    public AuthenticationService(UsuarioRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UsuarioService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.usuarioService = userService;
    }

    public ResponseDTO signup(SignUpRequest request) {
        if (usuarioService.existsUserByEmail(request.getEmail())) {
            throw new EmailAlreadyExitsException();
        }
        if (usuarioService.existUserByNickname(request.getNickname())) {
            throw new UserAlreadyExistsException();
        }

        var user = new Usuario();
        user.setNickname(request.getNickname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        var jwt = jwtService.generateToken(user);

        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);
        ResponseDTO info = new ResponseDTO();
        info.setId(user.getId());
        info.setToken(jwt);
        info.setNickName(user.getNickname());
        info.setEmail(user.getEmail());
        return info;
    }

    public ResponseDTO signin(SigninRequest request) {
        Usuario user = userRepository.getUsuarioByEmail(request.getEmail());

        if (user == null) {
            user = userRepository.getUsuarioByNickname(request.getNickname());
        }

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new EmailPasswordException();
        }

        // Construir el DTO de respuesta con la información del usuario y el token JWT
        ResponseDTO info = new ResponseDTO();
        info.setId(user.getId());
        info.setNickName(user.getNickname());
        info.setEmail(user.getEmail());
        info.setEnlace_imagen(user.getEnlace_imagen());
        info.setEnlace_portada(user.getEnlace_portada());
        // Generar el token JWT para el usuario autenticado
        String jwt = jwtService.generateToken(user);
        info.setToken(jwt);

        return info;
    }

}
