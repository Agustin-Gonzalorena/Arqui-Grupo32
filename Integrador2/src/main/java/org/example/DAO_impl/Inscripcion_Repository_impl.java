package org.example.DAO_impl;

import org.example.ConexionEntityManager;
import org.example.entidades.Carrera;
import org.example.entidades.Estudiante;
import org.example.entidades.Inscripcion;
import org.example.entidades_Repository.Inscripcion_Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class Inscripcion_Repository_impl implements Inscripcion_Repository {

    private static Inscripcion_Repository_impl instancia;
    public ConexionEntityManager conexion;

    private Inscripcion_Repository_impl() {
        this.conexion=ConexionEntityManager.getInstancia();
    }

    public static Inscripcion_Repository_impl getInstancia() {
        if(instancia==null){
            instancia = new Inscripcion_Repository_impl();
            return instancia;
        }else{
            return instancia;
        }
    }


    @Override
    public void matricular(Estudiante e, Carrera c, LocalDate antiguedad,boolean graduado) {
        EntityManager em = conexion.getConexion();
        em.getTransaction().begin();

        long dni_estudiante=e.getDNI();
        long id_carrera=c.getId();

        String query="select i from Inscripcion i " +
                        "join i.estudiante e " +
                        "join i.carrera c " +
                        "where e.DNI = :dni_estudiante and c.id = :id_carrera";
        Inscripcion existe= em.createQuery(query,Inscripcion.class)
                .setParameter("dni_estudiante",dni_estudiante)
                .setParameter("id_carrera",id_carrera)
                .getSingleResult();
        if(existe==null){
            Inscripcion inscribir=new Inscripcion(e,c,antiguedad,graduado);
            em.persist(inscribir);
        }else{
            System.out.println("este estudiante ya esta inscripto en esta carrera");
        }

        em.getTransaction().commit();
        conexion.closeConection(em);
    }


}
