package org.example.DAO_impl;

import org.example.ConexionEntityManager;
import org.example.entidades.Carrera;
import org.example.entidades_Repository.Carrera_Repository;

import javax.persistence.EntityManager;
import java.util.List;

public class Carrera_Repository_impl implements Carrera_Repository {

    private EntityManager em;

    public Carrera_Repository_impl(EntityManager em) {
        this.em = em;
    }

    public void agregar(Carrera carrera) {
        Carrera c = buscar(carrera);
        if(c==null){
            em.persist(carrera);
        }else{
            System.out.println("La carrera ya existe");
        }
    }
    public Carrera buscar(Carrera carrera) {
        return em.find(Carrera.class, carrera);
    }


    @Override
    public List<Carrera> getCarrerasIncriptosOrdenada() {
        String query="select c " +
                        "from Carrera c " +
                        "join c.inscripciones i " +
                        "join i.estudiante e " +
                        "group by c " +
                        "order by count(c) desc";
        List<Carrera> carreras = em.createQuery(query).getResultList();

        return carreras;
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
