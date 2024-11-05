package com.microservice.usuario.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cuenta {
    @Id
    @Column(name = "cuenta_id")
    private Long id;
    private Double saldo;

    @ManyToMany(mappedBy = "cuentas")
    private List<Usuario> usuarios;
}
