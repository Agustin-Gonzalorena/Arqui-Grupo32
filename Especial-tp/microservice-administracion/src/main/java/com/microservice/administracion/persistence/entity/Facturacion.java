package com.microservice.administracion.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Facturacion {
    @Id
    private Long id; // en realidad la PK deberia ser usuarioId+viajeId TODO
    private Long usuarioId;
    private Long viajeId;
    private Double montoCobrado;
    private LocalDate fechaFacturacion;

}
