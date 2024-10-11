package org.example.integrador3.Entidades.DTO;

public class CarreraConInscriptosYEgresados {
    private String nombre;
    private int anio;
    private long inscriptos;
    private long egresados;

    public CarreraConInscriptosYEgresados(String nombre, int anio, long inscriptos, long egresados) {
        this.nombre = nombre;
        this.anio = anio;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public long getInscriptos() {
        return inscriptos;
    }

    public void setInscriptos(long inscriptos) {
        this.inscriptos = inscriptos;
    }

    public long getEgresados() {
        return egresados;
    }

    public void setEgresados(long egresados) {
        this.egresados = egresados;
    }

    @Override
    public String toString() {
        return "CarreraInfo{" +
                "Carrera='" + nombre + '\'' +
                ", Año=" + anio +
                ", Inscriptos=" + inscriptos +
                ", Egresados=" + egresados +
                '}';
    }
}
