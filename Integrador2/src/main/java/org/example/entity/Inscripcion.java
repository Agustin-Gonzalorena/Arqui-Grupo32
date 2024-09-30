package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Inscripcion {

    @EmbeddedId
    private KeyInscripcion keyInscripcion;
    @Column
    private LocalDate fechaInscripcion;
    @Column
    private boolean graduado;


    public Inscripcion(Estudiante estudiante,Carrera carrera, LocalDate fechaInscripcion, boolean graduado) {
        keyInscripcion = new KeyInscripcion(estudiante,carrera);
        this.fechaInscripcion = fechaInscripcion;
        this.graduado = graduado;
    }

    public Inscripcion() {
        super();
    }



    public LocalDate getfechaInscripcion() {
        return fechaInscripcion;
    }

    public void setfechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                ", fechaInscripcion=" + fechaInscripcion +
                ", graduado=" + graduado +
                '}';
    }

}
