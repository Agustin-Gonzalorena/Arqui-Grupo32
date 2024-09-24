package org.example.DAO_impl;

import org.example.ConexionEntityManager;
import org.example.entidades.Carrera;
import org.example.entidades_Repository.Carrera_Repository;

import javax.persistence.EntityManager;
import java.util.List;

public class Carrera_Repository_impl implements Carrera_Repository {

    private static Carrera_Repository_impl instancia;
    public ConexionEntityManager conexion;

    private Carrera_Repository_impl() {
        this.conexion=ConexionEntityManager.getInstancia();
    }

    public static Carrera_Repository_impl getInstancia() {
        if(instancia==null){
            instancia = new Carrera_Repository_impl();
            return instancia;
        }else{
            return instancia;
        }
    }

    public void agregarCarrera(Carrera carrera) {
        EntityManager em = conexion.getConexion();
        em.getTransaction().begin();
        Carrera c = em.find(Carrera.class, carrera);
        if(c==null){
            em.persist(carrera);
        }else{
            System.out.println("La carrera ya existe");
        }
        em.getTransaction().commit();
        conexion.closeConection(em);
    }


    @Override
    public List<Carrera> getCarrerasIncriptosOrdenada() {
        EntityManager em = conexion.getConexion();
        em.getTransaction().begin();
        String query="select c " +
                        "from Carrera c " +
                        "join c.inscripciones i " +
                        "join i.estudiante e " +
                        "group by c " +
                        "order by count(c) desc";
        List<Carrera> carreras = em.createQuery(query).getResultList();
        em.getTransaction().commit();
        conexion.closeConection(em);
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
