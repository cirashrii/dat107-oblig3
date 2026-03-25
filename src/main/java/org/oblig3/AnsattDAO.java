package org.oblig3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AnsattDAO {

    private EntityManager em;

    public AnsattDAO(EntityManager em) {
        this.em = em;
    }

    public Ansatt finnAnsattMedId(int id) {
        return em.find(Ansatt.class, id);
    }

    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
        TypedQuery<Ansatt> query = em.createQuery(
                "SELECT a FROM Ansatt a WHERE a.brukernavn = :bn", Ansatt.class);
        query.setParameter("bn", brukernavn);
        return query.getSingleResult();
    }

    public List<Ansatt> finnAlleAnsatte() {
        TypedQuery<Ansatt> query = em.createQuery(
                "SELECT a FROM Ansatt a", Ansatt.class);
        return query.getResultList();
    }

    public void oppdaterAnsatt(int id, String nyStilling, Double nyLonn) {
        Ansatt a = em.find(Ansatt.class, id);
        if (a != null) {
            em.getTransaction().begin();
            if (nyStilling != null) a.setStilling(nyStilling);
            if (nyLonn != null) a.setMaanedslonn(nyLonn);
            em.getTransaction().commit();
        }
    }

    public void lagreAnsatt(Ansatt ansatt) {
        em.getTransaction().begin();
        em.persist(ansatt);
        em.getTransaction().commit();
    }
}

