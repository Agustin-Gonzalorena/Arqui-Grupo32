package com.microservice.viaje.presentation.dto;

import lombok.Data;

@Data
public class ViajeCreateDTO {
    private Long usuarioId;
    private Long monopatinId;

    public ViajeCreateDTO() {}
    public ViajeCreateDTO(Long usuarioId, Long monopatinId) {
        this.usuarioId = usuarioId;
        this.monopatinId = monopatinId;
    }
}
