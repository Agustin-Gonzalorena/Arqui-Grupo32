package org.example.DAO_impl;

import org.example.ConexionEntityManager;
import org.example.entidades.Carrera;
import org.example.entidades.Estudiante;
import org.example.entidades_Repository.Estudiante_Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class Estudiante_Repository_impl implements Estudiante_Repository {

    private EntityManager em;

    public Estudiante_Repository_impl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void agregar(Estudiante e) {
        Estudiante estudiante = buscar(e);
        if(estudiante==null) {
            em.persist(e);
        }else{
            System.out.println(estudiante.getNombre()+" ya esta registrado en la base");
        }
    }
    // Esto asi funciona? busca si existe buscando una clase?
    public Estudiante buscar(Estudiante estudiante){
        return em.find(Estudiante.class, estudiante);
    }
    @Override
    public List<Estudiante> getEstudiantesPorOrden(String orden) {
        String query="select e " +
                        "from Estudiante e " +
                        "order by :orden";
        List<Estudiante> estudiantesOrdenados = em.createQuery(query, Estudiante.class)
                .setParameter("orden", orden)
                .getResultList();
        if(estudiantesOrdenados!=null){
            return estudiantesOrdenados;
        }
        System.out.println("No hay estudiantes que se puedan ordenar de esta forma");
        return null;
    }

    @Override
    public Estudiante getEstudiantePorLibreta(int libreta) {
        String query="select e " +
                "from Estudiante e " +
                "where e.NroLibretaUniversitaria = :libreta";
        Estudiante estudiantesPorLibreta = em.createQuery(query, Estudiante.class)
                .setParameter("libreta", libreta)
                .getSingleResult();
        if(estudiantesPorLibreta!=null){
            return estudiantesPorLibreta;
        }
        System.out.println("No existe un estudiante con esta libreta");
        return null;
    }

    @Override
    public List<Estudiante> getEstudiantesPorGenero(String genero) {
        String query="select e " +
                "from Estudiante e " +
                "where e.genero = :genero";
        List<Estudiante> estudiantesPorGenero = em.createQuery(query, Estudiante.class)
                .setParameter("genero", genero)
                .getResultList();
        if(estudiantesPorGenero!=null){
            return estudiantesPorGenero;
        }
        System.out.println("No existen un estudiantes con este genero");
        return null;
    }

    @Override
    public List<Estudiante> getEstudiantesPorCarrera_ciudad(Carrera car, String ciudad) {
        long id_carrera= car.getId();
        String query="select e " +
                        "from Estudiante e " +
                        "join e.inscripciones i " +
                        "join i.carrera c " +
                        "where c.id = :id_carrera and e.CiudadResidencia = :ciudad";
        List<Estudiante> estudiantesPorCarrera_ciudad = em.createQuery(query, Estudiante.class)
                .setParameter("id_carrera", id_carrera)
                .setParameter("ciudad", ciudad)
                .getResultList();
        if(estudiantesPorCarrera_ciudad!=null){
            return estudiantesPorCarrera_ciudad;
        }
        System.out.println("No hay estudiantes que estudien esta carrera y vivan en esta ciudad");
        return null;
    }

}
