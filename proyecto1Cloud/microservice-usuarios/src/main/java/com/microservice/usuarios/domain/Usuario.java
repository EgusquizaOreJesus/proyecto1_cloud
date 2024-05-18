package com.microservice.usuarios.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data       // Crea los getter y setter
@Builder    // Crea un objeto con todos los atributos
@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    // Atributos
    // name
    // atributo obligatorio
    @Column(nullable = false)
    private String nickname;
    // email
    @Column(unique = true)
    private String email;

    // password
    private String password;

    private String image_path;

    private String background_picture;

    private String enlace_imagen;

    private String enlace_portada;

    @ElementCollection
    private Set<Long> hilosCreados = new HashSet<>();


    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection
    private Set<Long> respuestasParticipadas = new HashSet<>();

    @ElementCollection
    private Set<Long> estados = new HashSet<>();

    @ElementCollection
    private List<Long> respuestas = new ArrayList<>();


    public Long getId() {
        return id;
    }

        @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
