package org.example.entity.dto;

public class CarreraConInscriptos {
    int id;
    String nombre;
    Long cantidadInscriptos;

    public CarreraConInscriptos( int id, String nombre, Long cantidadInscriptos) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadInscriptos = cantidadInscriptos;
    }

    @Override
    public String toString() {
        return "CarreraConInscriptos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidadInscriptos=" + cantidadInscriptos +
                '}';
    }
}
