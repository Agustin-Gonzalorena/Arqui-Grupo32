package org.example.integrador3.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String nombre;
    @OneToMany(mappedBy = "carrera")
    @JsonIgnore
    private List<Inscripcion> inscripciones;

    public Carrera() {}

    public Carrera(String nombre) {
        this.nombre = nombre;
        inscripciones = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
