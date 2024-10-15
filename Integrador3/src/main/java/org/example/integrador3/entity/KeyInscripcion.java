package org.example.integrador3.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class KeyInscripcion implements Serializable {
    @ManyToOne
    @JoinColumn(name = "estudiante_dni",nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "carrera_id",nullable = false)
    private Carrera carrera;

}
