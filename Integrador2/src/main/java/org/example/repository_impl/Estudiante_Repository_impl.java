package org.example.repository_impl;

import org.example.entity.Carrera;
import org.example.entity.Estudiante;
import org.example.repository.Estudiante_Repository;

import javax.persistence.EntityManager;
import java.util.List;

public class Estudiante_Repository_impl implements Estudiante_Repository {
    private EntityManager em;

    public Estudiante_Repository_impl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void agregar(Estudiante e) {
        try {
            em.persist(e);
        } catch (Exception ex) {
            System.out.println("Error al agregar el estudiante");
            ex.printStackTrace();
        }
    }

    @Override
    public Estudiante buscarPorDNI(int id) {
        try {
            return em.find(Estudiante.class, id);
        }catch (Exception ex) {
            System.out.println("Error al buscar el estudiante");
            return null;
        }
    }

    @Override
    public List<Estudiante> getEstudiantesByOrden(String orden) {
        String query="SELECT e " +
                     "FROM Estudiante e " +
                     "ORDER BY e. " + orden;
        try {
            List<Estudiante> estudiantesOrdenados = em
                    .createQuery(query, Estudiante.class)
                    .getResultList();
            return estudiantesOrdenados;
        }catch (Exception ex) {
            System.out.println("No hay estudiantes que se puedan ordenar de esta forma");
            return null;
        }
    }

    @Override
    public Estudiante getEstudiantePorLibreta(int libreta) {
        String query="select e " +
                "from Estudiante e " +
                "where e.NroLibretaUniversitaria = :libreta";
        try {
            Estudiante estudiantesPorLibreta = em.createQuery(query, Estudiante.class)
                    .setParameter("libreta", libreta)
                    .getSingleResult();
            return estudiantesPorLibreta;
            }
        catch (Exception ex) {
            System.out.println("No existe un estudiante con esta libreta");
            return null;
        }
    }

    @Override
    public List<Estudiante> getEstudiantesPorGenero(String genero) {
        String query="select e " +
                "from Estudiante e " +
                "where e.genero = :genero";
        try{
            List<Estudiante> estudiantesPorGenero = em.createQuery(query, Estudiante.class)
                .setParameter("genero", genero)
                .getResultList();
            return estudiantesPorGenero;
        }catch (Exception ex) {
            System.out.println("No existen un estudiantes con este genero");
            return null;
        }
    }

    @Override
    public List<Estudiante> getEstudiantesPorCarreraCiudad(Carrera car, String ciudad) {
        int id_carrera= car.getId();
        String query="select e " +
                        "from Estudiante e " +
                        "join e.inscripciones i " +
                        "join i.keyInscripcion.carrera c " +
                        "where c.id = :id_carrera and e.CiudadResidencia = :ciudad";
        try {
            List<Estudiante> estudiantesPorCarrera_ciudad = em.createQuery(query, Estudiante.class)
                    .setParameter("id_carrera", id_carrera)
                    .setParameter("ciudad", ciudad)
                    .getResultList();
            return estudiantesPorCarrera_ciudad;
        }catch (Exception ex) {
            System.out.println("No hay estudiantes que estudien esta carrera y vivan en esta ciudad");
            return null;
        }
    }

}
