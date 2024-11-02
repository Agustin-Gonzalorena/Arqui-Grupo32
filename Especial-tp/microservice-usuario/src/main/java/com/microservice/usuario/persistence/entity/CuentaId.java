package com.microservice.usuario.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CuentaId {
    @Id
    @Column(name = "cuenta_id")
    private Long cuentaId;
}
