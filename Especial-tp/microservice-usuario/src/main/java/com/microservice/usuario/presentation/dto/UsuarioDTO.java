package com.microservice.usuario.presentation.dto;

import com.microservice.usuario.persistence.entity.Usuario;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private int telefono;
    private LocalDate fechaDeAlta;
    private Boolean ban;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.ban = usuario.getBan();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.email = usuario.getEmail();
        this.telefono = usuario.getTelefono();
        this.fechaDeAlta = usuario.getFechaDeAlta();
    }
}
