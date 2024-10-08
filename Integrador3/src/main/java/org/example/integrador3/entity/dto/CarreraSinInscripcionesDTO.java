package org.example.integrador3.entity.dto;


import lombok.Data;

@Data
public class CarreraSinInscripcionesDTO {
    private long id;
    private String nombre;

    public CarreraSinInscripcionesDTO(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
