package com.microservice.parada.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ParadaCreateDTO {
    private Double latitud;
    private Double longitud;

    public ParadaCreateDTO() {}
    public ParadaCreateDTO(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
