package org.example.integrador3.entity.dto;

import lombok.Data;

@Data
public class CarreraConInscriptosDTO {
    private long id;
    private String nombre;
    private long cantidadInscriptos;

    public CarreraConInscriptosDTO(long id, String nombre, long cantidadInscriptos) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadInscriptos = cantidadInscriptos;
    }
}
