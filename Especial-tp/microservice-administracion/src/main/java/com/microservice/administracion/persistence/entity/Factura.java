package com.microservice.administracion.persistence.entity;

import com.microservice.administracion.presentation.dto.FacturaDTO;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
public class Factura {
    @EmbeddedId
    private KeyFactura idFactura;
    private Double montoCobrado;
    private LocalDate fecha;

    public Factura(FacturaDTO facturaDTO) {
        KeyFactura idFactura = new KeyFactura(facturaDTO.getUsuarioId(),facturaDTO.getViajeId());
        this.idFactura = idFactura;
        this.montoCobrado = facturaDTO.getMontoCobrado();
        this.fecha=LocalDate.now();
    }
}
