package org.example.integrador3.repository;

import org.example.integrador3.entity.Carrera;
import org.example.integrador3.entity.dto.CarreraReporteDTO;
import org.example.integrador3.entity.dto.CarreraConInscriptosDTO;
import org.example.integrador3.entity.dto.CarreraSinInscripcionesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepo extends JpaRepository<Carrera, Long> {

    @Query("SELECT c FROM Carrera c WHERE c.id=:id")
    Carrera getById(long id);

    @Query("SELECT new org.example.integrador3.entity.dto.CarreraSinInscripcionesDTO(c.id,c.nombre)" +
            "FROM Carrera c")
    List<CarreraSinInscripcionesDTO> getAll();

    @Query("SELECT new org.example.integrador3.entity.dto.CarreraConInscriptosDTO(c.id,c.nombre,count(e.DNI))" +
            "FROM Carrera c " +
            "LEFT JOIN c.inscripciones i " +
            "LEFT JOIN i.keyInscripcion.estudiante e " +
            "GROUP BY c " +
            "ORDER BY count(c) DESC")
    List<CarreraConInscriptosDTO> getCarrerasInscriptosOrdenada();

    @Query("SELECT new org.example.integrador3.entity.dto.CarreraReporteDTO(c.nombre,YEAR(i.fechaInscripcion),COUNT(i)," +
            "SUM(CASE WHEN i.fechaGraduacion IS NOT NULL THEN 1 ELSE 0 END )) " +
            "FROM Inscripcion i " +
            "LEFT JOIN i.keyInscripcion.carrera c " +
            "GROUP BY c.nombre, YEAR(i.fechaInscripcion) " +
            "ORDER BY c.nombre ASC, YEAR(i.fechaInscripcion) ASC")
    List<CarreraReporteDTO> getReporte();
}
