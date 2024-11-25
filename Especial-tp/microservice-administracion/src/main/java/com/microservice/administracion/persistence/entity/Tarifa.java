package com.microservice.administracion.persistence.entity;

import com.microservice.administracion.presentation.dto.TarifaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double precio;
    private Double extra;
    private LocalDate fechaVigencia;

    public Tarifa(TarifaDTO tarifaDTO){
        this.precio = tarifaDTO.getPrecio();
        this.extra = tarifaDTO.getExtra();
        this.fechaVigencia = tarifaDTO.getFechaVigencia();
    }
}
