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

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        AnsattDAO dao = new AnsattDAO(em);

        Scanner in = new Scanner(System.in);

        boolean ferdig = false;

        while (!ferdig) {

            System.out.println("\n--- MENY ---");
            System.out.println("1: Finn ansatt (ID)");
            System.out.println("2: Finn ansatt (brukernavn)");
            System.out.println("3: List alle ansatte");
            System.out.println("4: Oppdater ansatt");
            System.out.println("5: Ny ansatt");
            System.out.println("0: Avslutt");

            System.out.print("Velg: ");
            int valg = in.nextInt();

            switch (valg) {

                case 1:
                    System.out.print("Skriv ansatt-id: ");
                    int id = in.nextInt();

                    Ansatt a1 = dao.finnAnsattMedId(id);

                    if (a1 != null) {
                        System.out.println(a1);
                    } else {
                        System.out.println("Fant ikke ansatt.");
                    }
                    break;

                case 2:
                    System.out.print("Brukernavn: ");
                    String bn = in.next();

                    Ansatt a2 = dao.finnAnsattMedBrukernavn(bn);

                    if (a2 != null) {
                        System.out.println(a2);
                    } else {
                        System.out.println("Fant ikke ansatt.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Alle ansatte ---");
                    dao.finnAlleAnsatte().forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Ansatt-id: ");
                    int idOppdatert = in.nextInt();

                    System.out.print("Ny stilling (skriv - for å hoppe over): ");
                    String stilling = in.next();

                    if (stilling.equals("-")) {
                        stilling = null;
                    }

                    System.out.print("Ny lønn (skriv -1 for å hoppe over): ");
                    double lonnInput = in.nextDouble();
                    Double lonn = (lonnInput == -1) ? null : lonnInput;

                    dao.oppdaterAnsatt(idOppdatert, stilling, lonn);
                    System.out.println("Oppdatert.");
                    break;

                case 5:
                    Ansatt ny = new Ansatt();

                    // Enkelt – du kan hardkode eller spørre bruker
                    System.out.print("Fornavn: ");
                    ny.setFornavn(in.next());

                    System.out.print("Etternavn: ");
                    ny.setEtternavn(in.next());

                    System.out.print("Brukernavn: ");
                    ny.setBrukernavn(in.next());

                    System.out.print("Stilling: ");
                    ny.setStilling(in.next());

                    System.out.print("Månedslønn: ");
                    ny.setMaanedslonn(in.nextDouble());

                    // resten kan settes senere (avdeling osv.)
                    dao.lagreAnsatt(ny);

                    System.out.println("Ny ansatt lagret.");
                    break;

                case 0:
                    ferdig = true;
                    break;

                default:
                    System.out.println("Ugyldig valg.");
            }
        }

        em.close();
        in.close();
    }
}