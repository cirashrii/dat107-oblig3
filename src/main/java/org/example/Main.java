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

        System.out.print("Skrive et ansatt id:");

        int temp = in.nextInt();

        Ansatt p = finnPersonMedID(temp);
        System.out.println(p);
        in.close();
    }

    private static Ansatt finnPersonMedID(int ansattid) {

        System.out.println("Kobler til database...");
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Ansatt.class, ansattid);
        } finally {
            em.close();
        }
    }
}
