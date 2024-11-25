package com.microservice.administracion.presentation.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TarifaDTO {
    private Double precio;
    private Double extra;
    private LocalDate fechaVigencia;
}
