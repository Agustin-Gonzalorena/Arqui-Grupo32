package com.microservice.administracion.presentation.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViajeDTO {
    private Long id;
    private Long usuarioId;
    private Long monopatinId;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private LocalDateTime inicioPausa;
    private LocalDateTime finPausa;
    private Double kilometros;
}
