package org.example.integrador3.Repository;

import org.example.integrador3.Entidades.Carrera;
import org.example.integrador3.Entidades.DTO.CarreraConInscriptosYEgresados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Carrera_Repository extends JpaRepository<Carrera, Long> {

    @Query("select c from Carrera c " +
            "join c.inscripciones i " +
            "join i.keyInscripcion.estudiante e " +
            "group by c.id " +
            "order by count(c)")
    public List<Carrera> getCarrerasConInscriptosOrdenada();



    @Query("SELECT new org.example.integrador3.Entidades.DTO.CarreraConInscriptosYEgresados("
            + "c.nombre, YEAR(i.fechaInscripcion), "
            + "COUNT(i), "
            + "SUM(CASE WHEN i.graduado = true THEN 1 ELSE 0 END) )"
            + "FROM Inscripcion i "
            + "LEFT JOIN i.keyInscripcion.carrera c "
            + "GROUP BY c.nombre, YEAR(i.fechaInscripcion) "
            + "ORDER BY c.nombre ASC, YEAR(i.fechaInscripcion) ASC")
    public List<CarreraConInscriptosYEgresados> getCarrerasConCantInscriptosEgresados();


}
