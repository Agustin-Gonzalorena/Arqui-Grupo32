package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Inscripcion {

    @EmbeddedId
    private KeyInscripcion keyInscripcion;
    @Column
    private LocalDate antiguedad;
    @Column
    private boolean graduado;


    public Inscripcion(Estudiante estudiante,Carrera carrera, LocalDate antiguedad, boolean graduado) {
        keyInscripcion = new KeyInscripcion(estudiante,carrera);
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }

    public Inscripcion() {
        super();
    }



    public LocalDate getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(LocalDate antiguedad) {
        this.antiguedad = antiguedad;
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
                ", antiguedad=" + antiguedad +
                ", graduado=" + graduado +
                '}';
    }

}
