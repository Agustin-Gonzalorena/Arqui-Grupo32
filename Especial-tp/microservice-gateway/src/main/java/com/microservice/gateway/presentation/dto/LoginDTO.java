package com.microservice.gateway.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {
    @NotNull( message = "El email es un campo requerido." )
    @NotEmpty( message = "El email es un campo requerido." )
    private String email;

    @NotNull( message = "La contraseña es un campo requerido." )
    @NotEmpty( message = "La contraseña es un campo requerido." )
    private String password;

    public String toString(){
        return "Mail: " + email + ", Password: [FORBIDDEN] ";
    }
}
