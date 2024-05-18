package com.microservice.usuarios.persistence;

import com.microservice.usuarios.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmail(String email);

    Usuario findByNickname(String nickname);

    Usuario getUsuarioByEmail(String email);

    Usuario getUsuarioByNickname(String nickname);

}
