package com.microservice.viaje.presentation.dto;

import lombok.Data;

@Data
public class FacturaDTO {
    private Long viajeId;
    private Long usuarioId;
    private Double montoCobrado;
}
