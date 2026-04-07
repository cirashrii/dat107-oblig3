package org.oblig3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.Map;

public class ProsjektDAO {
    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("oblig3",
            Map.of("jakarta.persistence.jdbc.password", "pass"));

    public Prosjekt nyttProsjekt(int prosjektid, String navn, String beskrivelse){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Prosjekt ny = new Prosjekt(prosjektid, navn, beskrivelse);
        try{
            tx.begin();
            em.persist(ny);
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
        return ny;
    }

    public Prosjekt finnProsjektMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Prosjekt.class, id);
        } finally {
            em.close();
        }
    }
}

