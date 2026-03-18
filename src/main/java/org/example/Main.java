package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {
    private static EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("grethes-persistence-unit");
    }

    /* ------------------------------------------------------------------- */

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Skrive et brukernavn:");

        String temp = in.nextLine();

        Ansatt p = finnPersonMedBrukernavn(temp);
        System.out.println(p);
        in.close();
    }

    private static Ansatt finnPersonMedBrukernavn(String brukernavn) {

        System.out.println("Kobler til database...");
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Ansatt.class, brukernavn);
        } finally {
            em.close();
        }
    }
}
