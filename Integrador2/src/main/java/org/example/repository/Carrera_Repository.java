package org.example.repository;

import org.example.entity.Carrera;
import org.example.entity.dto.CarreraConInscriptosYEgresados;

import java.util.List;

public interface Carrera_Repository {
    public void agregar(Carrera carrera);
    public Carrera buscarPorId(int id);
    public List<Carrera> getCarrerasIncriptosOrdenada();
    public List<CarreraConInscriptosYEgresados> generarReportes();
}
