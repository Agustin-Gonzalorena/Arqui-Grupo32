package org.example.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante implements Serializable {

    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String genero;
    @Id
    private int DNI;
    @Column
    private String CiudadResidencia;
    @Column(unique = true)
    private int NroLibretaUniversitaria;
    @OneToMany (mappedBy = "keyInscripcion.estudiante")
    private List<Inscripcion> inscripciones;

    public Estudiante(String nombre, String apellido, String genero,
                      int dni, String ciudad, int nroLibreta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.DNI = dni;
        this.CiudadResidencia = ciudad;
        this.NroLibretaUniversitaria = nroLibreta;
        inscripciones = new ArrayList<Inscripcion>();
    }

    public Estudiante() {
        super();
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDNI() {
        return DNI;
    }


    public String getCiudadResidencia() {
        return CiudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        CiudadResidencia = ciudadResidencia;
    }

    public int getNroLibretaUniversiria() {
        return NroLibretaUniversitaria;
    }

    public void setNroLibretaUniversiria(int nroLibretaUniversiria) {
        NroLibretaUniversitaria = nroLibretaUniversiria;
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
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", genero='" + genero + '\'' +
                ", DNI=" + DNI +
                ", CiudadResidencia='" + CiudadResidencia + '\'' +
                ", NroLibretaUniversiria=" + NroLibretaUniversitaria +
                '}';
    }

}
