package org.example.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String nombre;
    @OneToMany (mappedBy = "carrera")
    private List<Inscripcion> inscripciones;

    public Carrera(String nombre) {
        this.nombre = nombre;
        this.inscripciones=new ArrayList<Inscripcion>();
    }

    public Carrera() {
        super();
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", inscripciones=" + inscripciones +
                '}';
    }

}
