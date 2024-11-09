package com.microservice.viaje.presentation.dto;

import lombok.Data;

@Data
public class MonopatinResponseDTO {
    private Long id;
    private double kilometros;
    private boolean enMantenimiento;
    private double tiempoSinPausa;
    private double tiempoConPausa;
    private String ultimaParadaId;
}
