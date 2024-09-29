package org.example.repository_impl;

import org.example.entity.Carrera;
import org.example.entity.dto.CarreraConInscriptosYEgresados;
import org.example.repository.Carrera_Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carrera_Repository_impl implements Carrera_Repository {

    private EntityManager em;

    public Carrera_Repository_impl(EntityManager em) {
        this.em = em;
    }

    public void agregar(Carrera carrera) {
        try {
            em.persist(carrera);
        } catch (Exception e) {
            System.out.println("Error al agregar el carrera");
            e.printStackTrace();
        }
    }

    public Carrera buscarPorId(int id) {
        return em.find(Carrera.class, id);
    }


    @Override
    public List<Carrera> getCarrerasIncriptosOrdenada() {
        String query = "select c " +
                "from Carrera c " +
                "join c.inscripciones i " +
                "join i.estudiante e " +
                "group by c " +
                "order by count(c) desc";
        try {
            List<Carrera> carreras = em.createQuery(query).getResultList();
            return carreras;
        } catch (Exception e) {
            System.out.println("Error al buscar los carreras");
            return null;
        }

    }

    public List<CarreraConInscriptosYEgresados> generarReportes() {
        String query = "SELECT c.nombre, YEAR(i.antiguedad), "
                    + "COUNT(i), "
                    + "SUM(CASE WHEN i.graduado = true THEN 1 ELSE 0 END) "
                    + "FROM Inscripcion i "
                    + "LEFT JOIN i.carrera c "
                    + "GROUP BY c.nombre, YEAR(i.antiguedad) "
                    + "ORDER BY c.nombre ASC, YEAR(i.antiguedad) ASC";

        List<Object[]> results = em.createQuery(query).getResultList();

        List<CarreraConInscriptosYEgresados> carreras = new ArrayList<>();
        for (Object[] fila : results) {
            String nombre = (String) fila[0];
            int anio = (Integer) fila[1];
            long inscriptos = (Long) fila[2];
            long egresados = (Long) fila[3];

            CarreraConInscriptosYEgresados c = new CarreraConInscriptosYEgresados(nombre, anio, inscriptos, egresados);
            carreras.add(c);
        }
        return carreras;
    }
}