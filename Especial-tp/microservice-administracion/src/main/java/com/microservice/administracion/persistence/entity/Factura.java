package com.microservice.administracion.persistence.entity;

import com.microservice.administracion.presentation.dto.ViajeDTO;
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

    public Factura(ViajeDTO viajeDTO,double cobrado) {
        KeyFactura idFactura = new KeyFactura(viajeDTO.getUsuarioId(),viajeDTO.getId());
        this.idFactura = idFactura;
        this.montoCobrado = cobrado;
        this.fecha=LocalDate.now();
    }
}
