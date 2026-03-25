package org.oblig3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AvdelingDAO {

    private EntityManager em;

    public AvdelingDAO(EntityManager em) {
        this.em = em;
    }

    public Avdeling finnAvdelingMedId(int avdeling) {
        return em.find(Avdeling.class, avdeling);
    }

}

