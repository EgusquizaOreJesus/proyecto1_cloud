package com.microservice.usuarios.seguridad.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "El campo 'nickname' es obligatorio.")
    private String nickname;
    @NotBlank(message = "El campo 'email' es obligatorio.")
    @Email(message = "El campo 'email' debe ser una dirección de correo válida.")
    private String email;
    @NotBlank(message = "El campo 'password' es obligatorio.")
    private String password;

    Long id;

}
