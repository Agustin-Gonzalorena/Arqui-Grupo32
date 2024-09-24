package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionEntityManager {

    private static ConexionEntityManager instancia;
    private EntityManagerFactory emf;

    private ConexionEntityManager() {
        emf = Persistence.createEntityManagerFactory("pedro");

    }

    public static ConexionEntityManager getInstancia() {
        if(instancia==null){
            instancia = new ConexionEntityManager();
            return instancia;
        }else{
            return instancia;
        }
    }

    public EntityManager getConexion() {
        return emf.createEntityManager();
    }

    public void closeConection(EntityManager em) {
        if(em.isOpen()){
            em.close();
        }
        if(emf.isOpen()){
            emf.close();
        }
    }

}
