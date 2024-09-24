package org.example.entidades_Repository;

import org.example.entidades.Carrera;
import org.example.entidades.Estudiante;

import java.util.List;

public interface Estudiante_Repository {

    public void darDeAlta(Estudiante e);
    public List<Estudiante> getEstudiantesPorOrden(String orden);
    public Estudiante getEstudiantePorLibreta(int libreta);
    public List<Estudiante> getEstudiantesPorGenero(String genero);
    public List<Estudiante> getEstudiantesPorCarrera_ciudad(Carrera c , String ciudad);

}
