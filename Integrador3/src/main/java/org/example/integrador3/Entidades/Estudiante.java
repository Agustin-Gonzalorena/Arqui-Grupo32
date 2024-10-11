package org.example.integrador3.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante {

    @Id
    private long dni;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudadResidencia;
    @Column
    private int nroLibretaUniversitaria;
    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Inscripcion> inscripciones;

    public Estudiante() {}

    public Estudiante(long dni, String nombre, String apellido, int edad,
                      String genero, String ciudad, int nroLibretaUniversitaria) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudadResidencia = ciudad;
        this.nroLibretaUniversitaria = nroLibretaUniversitaria;
        inscripciones = new ArrayList<>();
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public int getNroLibretaUniversitaria() {
        return nroLibretaUniversitaria;
    }

    public void setNroLibretaUniversitaria(int nroLibretaUniversitaria) {
        this.nroLibretaUniversitaria = nroLibretaUniversitaria;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", ciudadResidencia='" + ciudadResidencia + '\'' +
                ", nroLibretaUniversitaria=" + nroLibretaUniversitaria +
                ", inscripciones=" + inscripciones +
                '}';
    }

}
