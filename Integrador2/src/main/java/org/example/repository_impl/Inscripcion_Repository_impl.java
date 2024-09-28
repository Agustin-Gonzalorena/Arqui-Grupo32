package org.example.repository_impl;

import org.example.ConexionEntityManager;
import org.example.entity.Carrera;
import org.example.entity.Estudiante;
import org.example.entity.Inscripcion;
import org.example.repository.Inscripcion_Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class Inscripcion_Repository_impl implements Inscripcion_Repository {

    private EntityManager em;

    public Inscripcion_Repository_impl(EntityManager em) {
        this.em = em;
    }

    public void agregar(Inscripcion inscripcion) {
        try{
            em.persist(inscripcion);
        }catch(Exception e){
            System.out.println("Error al agregar el inscripcion");
            e.printStackTrace();
        }
    }

}
