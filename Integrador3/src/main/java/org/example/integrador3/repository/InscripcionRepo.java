package org.example.integrador3.repository;

import org.example.integrador3.entity.Inscripcion;
import org.example.integrador3.entity.KeyInscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InscripcionRepo extends JpaRepository<Inscripcion, Long> {

    @Query("SELECT i FROM Inscripcion i where i.keyInscripcion = :keyInscripcion")
    Inscripcion getById(KeyInscripcion keyInscripcion);
}
