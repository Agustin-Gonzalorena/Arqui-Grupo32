package org.example;

import org.example.DAO_impl.Carrera_Repository_impl;
import org.example.DAO_impl.Estudiante_Repository_impl;
import org.example.entidades.Carrera;
import org.example.entidades.Estudiante;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        Carrera c1 = new Carrera("Tudai");
        Estudiante e1 = new Estudiante("Lionel","Messi","Masculino",37890191,"Rosario",10);


        EntityManager em = ConexionEntityManager.getInstancia().getConexion();
        em.getTransaction().begin();
        Carrera_Repository_impl repoC = new Carrera_Repository_impl(em);
        repoC.agregar(c1);

        Estudiante_Repository_impl repoE = new Estudiante_Repository_impl(em);
        repoE.agregar(e1);


        em.getTransaction().commit();
        em.close();


    }
}