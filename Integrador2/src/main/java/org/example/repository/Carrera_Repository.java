package org.example.repository;

import org.example.entity.Carrera;
import org.example.entity.dto.CarreraConInscriptos;
import org.example.entity.dto.CarreraConInscriptosYEgresados;

import java.util.List;

public interface Carrera_Repository {
    public void agregar(Carrera carrera);
    public Carrera buscarPorId(int id);
    public List<CarreraConInscriptos> getCarrerasIncriptosOrdenada();
    public List<CarreraConInscriptosYEgresados> generarReportes();
}
