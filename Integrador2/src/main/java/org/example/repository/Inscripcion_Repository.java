package org.example.repository;

import org.example.entity.Carrera;
import org.example.entity.Estudiante;
import org.example.entity.Inscripcion;

import java.time.LocalDate;

public interface Inscripcion_Repository {
    public void agregar(Inscripcion inscripcion);
}
