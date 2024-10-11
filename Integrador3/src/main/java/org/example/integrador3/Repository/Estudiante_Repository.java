package org.example.integrador3.Repository;

import org.example.integrador3.Entidades.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Estudiante_Repository extends JpaRepository<Estudiante, Long> {

    @Query("select e from Estudiante e order by :ordenado")
    public List<Estudiante> getEstudiantesOrdenados(String ordenado);

    @Query("select e from Estudiante e where e.nroLibretaUniversitaria = :libreta")
    public Estudiante getEstudiantePorNroLibreta(int libreta);

    @Query("select e from Estudiante e where e.genero = :genero")
    public List<Estudiante> getEstudiantesPorGenero(String genero);

    @Query("select e from Estudiante e " +
            "join e.inscripciones i " +
            "join i.keyInscripcion.carrera c " +
            "where c.nombre = :nombre_carrera and e.ciudadResidencia = :ciudad ")
    public List<Estudiante> getEstudiantesPorCarreraCiudad(String nombre_carrera, String ciudad);

}
