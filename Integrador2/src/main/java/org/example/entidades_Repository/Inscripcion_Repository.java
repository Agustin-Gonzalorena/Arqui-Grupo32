package org.example.entidades_Repository;

import org.example.entidades.Carrera;
import org.example.entidades.Estudiante;

import java.time.LocalDate;
import java.util.List;

public interface Inscripcion_Repository {

    public void matricular(Estudiante e, Carrera c, LocalDate antiguedad, boolean graduado);

}
