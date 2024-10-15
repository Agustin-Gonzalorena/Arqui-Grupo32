package org.example.integrador3.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InscripcionDTO {
    @NotNull(message = "Falta el dni del estudiante.")
    private int estudiante_dni;
    @NotNull(message = "Falta el id de la carrera.")
    private long carrera_id;

    public InscripcionDTO(int estudiante_dni, long carrera_id) {
        this.estudiante_dni = estudiante_dni;
        this.carrera_id = carrera_id;
    }
}
