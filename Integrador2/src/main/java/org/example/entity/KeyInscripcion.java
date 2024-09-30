package org.example.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class KeyInscripcion implements Serializable {

    @ManyToOne
    @JoinColumn(name = "estudiante_dni", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "carrera_id", nullable = false)
    private Carrera carrera;

    public KeyInscripcion(Estudiante estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
    }

    public KeyInscripcion() {

    }
}
