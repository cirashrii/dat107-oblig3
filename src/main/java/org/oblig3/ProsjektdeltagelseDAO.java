package org.oblig3;

import jakarta.persistence.*;
import java.util.Map;

public class ProsjektdeltagelseDAO {
    private EntityManager em;

    public ProsjektdeltagelseDAO(EntityManager em) {
        this.em = em;
    }

    public void registerer(Prosjekt prosjekt, Ansatt ansatt, String rolle, int timer){
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            Prosjektdeltagelse ny = new Prosjektdeltagelse();
            ny.setProsjekt(prosjekt);
            ny.setAnsatt(ansatt);
            ny.setRolle(rolle);
            ny.setTimer(timer);
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
    }

    public void oppdater(int ansattId, int prosjektId, int timer){
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Prosjektdeltagelse p = em.createQuery(
                            "SELECT p FROM Prosjektdeltagelse p WHERE p.ansatt.ansattid = :ansattId AND p.prosjekt.prosjektId = :prosjektId", Prosjektdeltagelse.class)
                    .setParameter("ansattId", ansattId)
                    .setParameter("prosjektId", prosjektId)
                    .getSingleResult();
            p.setTimer(timer);
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public void skrivUtProsjekt(int prosjektId) {
        int total = 0;
        try {
            Prosjekt p = em.find(Prosjekt.class, prosjektId);
            Prosjektdeltagelse d = em.find(Prosjektdeltagelse.class, prosjektId);
            System.out.println(prosjektId + " : " + p.getProsjektnavn());
            for (Ansatt a : p.getAlleAnsatte()) {
                System.out.println(a);
                total += d.getTimer();
            }
            System.out.println("Total timer i prosjektet: " + total);
        } finally {
            em.close();
        }

    }
}

