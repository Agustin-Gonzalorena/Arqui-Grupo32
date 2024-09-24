package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionEntityManager {

    private static ConexionEntityManager instancia;
    private EntityManagerFactory emf;

    private ConexionEntityManager() {}

    public static ConexionEntityManager getInstancia() {
        if(instancia==null){
            instancia = new ConexionEntityManager();
            return instancia;
        }else{
            return instancia;
        }
    }

    public EntityManager getConexion() {
        emf = Persistence.createEntityManagerFactory("pedro");
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public void closeConection(EntityManager em) {
        em.close();
        this.emf.close();
    }

}
