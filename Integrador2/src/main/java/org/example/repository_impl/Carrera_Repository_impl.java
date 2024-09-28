package org.example.repository_impl;

import org.example.entity.Carrera;
import org.example.repository.Carrera_Repository;

import javax.persistence.EntityManager;
import java.util.List;

public class Carrera_Repository_impl implements Carrera_Repository {

    private EntityManager em;

    public Carrera_Repository_impl(EntityManager em) {
        this.em = em;
    }

    public void agregar(Carrera carrera) {
        try{
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
        String query="select c " +
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



    //----------------------------------------

    //PUNTO 3

    //----------------------------------------


    public void generarReporte(){
        /*
SELECT
    c.nombre,
    COUNT(i) AS inscriptos,
    SUM(CASE WHEN graduado = TRUE THEN 1 ELSE 0 END) AS cantidad_egresados,
    i.antiguedad
FROM
    Carrera c
LEFT JOIN
    c.inscripciones i
JOIN
    i.estudiante e
GROUP BY
    c.nombre,
    i.antiguedad
ORDER BY
    c.nombre,
    i.antiguedad;
        *
        * */

    }

}
