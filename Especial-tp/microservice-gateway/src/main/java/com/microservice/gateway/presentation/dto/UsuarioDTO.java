package com.microservice.gateway.presentation.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioDTO {
    private String email;
    private String password;
    private List<Authority> authorities;
}
