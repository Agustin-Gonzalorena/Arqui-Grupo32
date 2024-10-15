package org.example.integrador3.repository;

import org.example.integrador3.entity.Estudiante;
import org.example.integrador3.entity.dto.EstudianteSinInscripcionesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepo extends JpaRepository<Estudiante, Long> {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM Estudiante e WHERE e.DNI = :dni")
    boolean existsByDni(int dni);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM Estudiante e WHERE e.nroLibretaUniversitaria = :libreta")
    boolean existByNroLibreta(int libreta);

    @Query("SELECT e FROM Estudiante e WHERE e.DNI=:dni")
    Estudiante findByDni(int dni);

    @Query("SELECT new org.example.integrador3.entity.dto.EstudianteSinInscripcionesDTO(e.nombre,e.apellido,e.genero,e.DNI,e.ciudadResidencia,e.nroLibretaUniversitaria)" +
            "FROM Estudiante e WHERE e.nroLibretaUniversitaria=:libreta")
    EstudianteSinInscripcionesDTO getByNroLibreta(int libreta);

    @Query("SELECT new org.example.integrador3.entity.dto.EstudianteSinInscripcionesDTO(e.nombre,e.apellido,e.genero,e.DNI,e.ciudadResidencia,e.nroLibretaUniversitaria) " +
            "from Estudiante e " +
            "left join e.inscripciones i " +
            "left join i.keyInscripcion.carrera c " +
            "where ( :nombre IS NULL OR lower(e.nombre) = :nombre ) " +
            "AND ( :apellido IS NULL OR lower(e.apellido) = :apellido ) " +
            "AND ( :DNI IS NULL OR e.DNI = :DNI ) " +
            "AND ( :genero IS NULL OR lower(e.genero) = :genero) " +
            "AND ( :ciudad IS NULL OR lower(e.ciudadResidencia) = :ciudad ) " +
            "AND ( :libreta IS NULL OR e.nroLibretaUniversitaria = :libreta ) " +
            "AND (:carrera_id IS NULL OR c.id = :carrera_id)")
    Page<EstudianteSinInscripcionesDTO> search( String nombre, String apellido,Integer DNI,String genero,
                             String ciudad,Integer libreta,Integer carrera_id, Pageable pageable );
}


/*  @Query("SELECT new org.example.integrador3.entity.dto.EstudianteSinInscripcionesDTO(e.nombre,e.apellido,e.genero,e.DNI,e.ciudadResidencia,e.nroLibretaUniversitaria)" +
            " FROM Estudiante e JOIN e.inscripciones i JOIN i.keyInscripcion.carrera c "+
            "WHERE c.nombre = :carrera and e.ciudadResidencia=:ciudad")
    List<EstudianteSinInscripcionesDTO> getEstudiantesCarreraCiudad(String carrera, String ciudad);
*/