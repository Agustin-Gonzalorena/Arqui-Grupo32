package org.example.integrador3.entity.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstudianteSinInscripcionesDTO {
    @NotBlank(message = "El atributo nombre no puede ser null, estar vacío o tener solo espacios en blanco.")
    private String nombre;
    @NotBlank(message = "El atributo apellido no puede ser null, estar vacío o tener solo espacios en blanco.")
    private String apellido;
    @NotBlank(message = "El atributo genero no puede ser null, estar vacío o tener solo espacios en blanco.")
    private String genero;
    @NotNull(message = "El atributo dni no puede ser null o estar vacio.")
    private Integer DNI;
    @NotBlank(message = "El atributo ciudadResidencia no puede ser null, estar vacío o tener solo espacios en blanco.")
    private String ciudadResidencia;
    @NotNull(message = "El atributo nroLibretaUniversitaria no puede ser null o estar vacio.")
    private Integer nroLibretaUniversitaria;

    public EstudianteSinInscripcionesDTO(String nombre,String apellido,String genero,Integer DNI,String ciudadResidencia,Integer nroLibretaUniversitaria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.DNI = DNI;
        this.ciudadResidencia = ciudadResidencia;
        this.nroLibretaUniversitaria = nroLibretaUniversitaria;
    }

}
