package com.microservice.usuario.presentation.dto;

import com.microservice.usuario.persistence.entity.Authority;
import com.microservice.usuario.persistence.entity.Usuario;
import lombok.Data;

import java.util.Optional;
import java.util.Set;

@Data
public class UsuarioAuthorityDTO {
    private Long id;
    private String email;
    private String password;
    private Set<Authority> authorities;

    public UsuarioAuthorityDTO(Optional<Usuario> usuario) {
        this.id = usuario.get().getId();
        this.email = usuario.get().getEmail();
        this.password = usuario.get().getPassword();
        this.authorities = usuario.get().getAuthorities();
    }
}
