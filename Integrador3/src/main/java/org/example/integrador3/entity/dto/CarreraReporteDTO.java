package org.example.integrador3.entity.dto;

import lombok.Data;

@Data
public class CarreraReporteDTO {
    private String nombre;
    private int anio;
    private long inscriptos;
    private long egresados;

    public CarreraReporteDTO(String nombre, int anio, long inscriptos, long egresados) {
        this.nombre = nombre;
        this.anio = anio;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }
}
