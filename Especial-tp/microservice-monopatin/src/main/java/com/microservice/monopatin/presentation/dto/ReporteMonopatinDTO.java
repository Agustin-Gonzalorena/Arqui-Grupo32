package com.microservice.monopatin.presentation.dto;

import lombok.Data;

@Data
public class ReporteMonopatinDTO {
    private Long id;
    private Double kilometros;
    private Double tiempoDeUso;

    public ReporteMonopatinDTO(Long id, double kilometros, double tiempoDeUso) {
        this.id = id;
        this.kilometros = kilometros;
        this.tiempoDeUso = tiempoDeUso;
    }
}
