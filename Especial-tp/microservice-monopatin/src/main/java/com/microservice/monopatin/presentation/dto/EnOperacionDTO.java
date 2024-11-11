package com.microservice.monopatin.presentation.dto;

import lombok.Data;

@Data
public class EnOperacionDTO {
    private Long disponibles;
    private Long enMantenimiento;

    public EnOperacionDTO(Long disponibles, Long enMantenimiento) {
        this.disponibles = disponibles;
        this.enMantenimiento = enMantenimiento;
    }
}
