package org.example.integrador3.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstudianteConCarreraDTO {
    private String nombre;
    private String apellido;
    private String genero;
    private Integer dni;
    private String ciudad;
    private Integer nroLibretaUniversitaria;
    private Integer carrera_id;

    public EstudianteConCarreraDTO(String nombre, String apellido, String genero, Integer DNI, String ciudadResidencia, Integer nroLibretaUniversitaria, Integer carrera_id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.dni = DNI;
        this.ciudad = ciudadResidencia;
        this.nroLibretaUniversitaria = nroLibretaUniversitaria;
        this.carrera_id = carrera_id;
    }
}
