package org.example.integrador3.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Inscripcion {
    @EmbeddedId
    private KeyInscripcion keyInscripcion;
    private LocalDate fechaInscripcion;
    private LocalDate fechaGraduacion;

}
