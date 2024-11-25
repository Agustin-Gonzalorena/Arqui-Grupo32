package com.microservice.gateway.presentation.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Data
public class UsuarioDTO {
    private Long id;
    private String email;
    private String password;
    private List<Authority> authorities;
}
