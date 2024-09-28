package org.example.repository;

import org.example.entity.Carrera;
import org.example.entity.Estudiante;

import java.util.List;

public interface Estudiante_Repository {

    public void agregar(Estudiante e);
    public Estudiante buscarPorDNI(int id);
    public List<Estudiante> getEstudiantesByOrden(String orden);
    public Estudiante getEstudiantePorLibreta(int libreta);
    public List<Estudiante> getEstudiantesPorGenero(String genero);
    public List<Estudiante> getEstudiantesPorCarreraCiudad(Carrera c , String ciudad);

}
