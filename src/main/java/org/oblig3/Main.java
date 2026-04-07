package org.oblig3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.eclipse.persistence.sessions.Project;

import java.util.Scanner;

public class Main {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("grethes-persistence-unit");
    }

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        AnsattDAO andao = new AnsattDAO(em);
        AvdelingDAO avdao = new AvdelingDAO(em);
        ProsjektDAO prdao = new ProsjektDAO(em);
        ProsjektdeltagelseDAO prdetdao = new ProsjektdeltagelseDAO(em);

        Scanner in = new Scanner(System.in);

        boolean ferdig = false;

        while (!ferdig) {

            System.out.println("\n--- MENY ---");
            System.out.println("--- ANSATT OG AVDELING ---");
            System.out.println("1: Finn ansatt (ID)");
            System.out.println("2: Finn ansatt (brukernavn)");
            System.out.println("3: Finn avdeling (ID)");
            System.out.println("4: List alle ansatte");
            System.out.println("5: List alle ansatte i en valgfri avdeling");
            System.out.println("6: Oppdater ansatt");
            System.out.println("7: Ny ansatt");
            System.out.println("\n--- PROSJEKT ---");
            System.out.println("8: Nytt prosjekt");
            System.out.println("9: Registrere prosjektdeltagelse");
            System.out.println("10: Føre timer for en ansatt");
            System.out.println("0: Avslutt");

            System.out.print("Velg: ");
            int valg = in.nextInt();

            switch (valg) {

                case 1:
                    System.out.print("Skriv ansatt-id: ");
                    int id = in.nextInt();

                    Ansatt a1 = andao.finnAnsattMedId(id);

                    if (a1 != null) {
                        System.out.println(a1);
                    } else {
                        System.out.println("Fant ikke ansatt.");
                    }
                    break;

                case 2:
                    System.out.print("Brukernavn: ");
                    String bn = in.next();

                    Ansatt a2 = andao.finnAnsattMedBrukernavn(bn);

                    if (a2 != null) {
                        System.out.println(a2);
                    } else {
                        System.out.println("Fant ikke ansatt.");
                    }
                    break;

                case 3:
                    System.out.print("Skriv avdeling-id: ");
                    int aId = in.nextInt();

                    Avdeling av1 = avdao.finnAvdelingMedId(aId);

                    if (av1 != null) {
                        System.out.println(av1);
                    } else {
                        System.out.println("Fant ikke ansatt.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Alle ansatte ---");
                    System.out.println("OBS! Spesifiserer ikke hvem som er sjef.");
                    andao.finnAlleAnsatte().forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Skriv avdeling-id: ");
                    int aID = in.nextInt();
                    System.out.println("\n--- Alle ansatte i valgt avdeling: ---");
                    andao.finnAnsatteInnenforAvdeling(aID).forEach(System.out::println);
                    break;

                case 6:
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

                    andao.oppdaterAnsatt(idOppdatert, stilling, lonn);
                    System.out.println("Oppdatert.");
                    break;

                case 7:
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

                    System.out.print("Avdeling: ");
                    boolean avdelingFunnet = false;
                    Avdeling tempAvdeling = null;
                    while(!avdelingFunnet) {
                        tempAvdeling = avdao.finnAvdelingMedId(in.nextInt());
                        if (tempAvdeling != null) {
                            ny.setAvdeling(tempAvdeling);
                            avdelingFunnet = true;
                        } else {
                            System.out.print("Avdeling finnes ikke.\nAvdeling: ");
                        }
                    }

                    // resten kan settes senere (avdeling osv.)
                    andao.lagreAnsatt(ny);

                    System.out.println("Ny ansatt lagret.");
                    break;

                case 8:
                    System.out.print("Prosjekt-ID: ");
                    int nyProsjektid = in.nextInt();

                    System.out.print("Prosjekt navn (uten mellomrom): ");
                    String nyProsjektnavn = in.next();

                    System.out.print("Prosjektbeskrivelse (uten mellomrom): ");
                    String nyProsjektbeskrivelse = in.next();

                    prdao.nyttProsjekt(nyProsjektid,nyProsjektnavn,nyProsjektbeskrivelse);
                    break;

                case 9:
                    System.out.print("Prosjektid: ");
                    Prosjekt prosjektDeltagelseProsjekt = em.find(Prosjekt.class, in.nextInt());

                    System.out.print("Ansattid: ");
                    Ansatt prosjektDeltagelseAnsatt = em.find(Ansatt.class, in.nextInt());

                    System.out.print("Rolle: ");
                    String prosjektDeltagelseRolle = in.next();

                    System.out.print("Timer: ");
                    int prosjektDeltagelseTimer = in.nextInt();

                    prdetdao.registerer(prosjektDeltagelseProsjekt, prosjektDeltagelseAnsatt, prosjektDeltagelseRolle, prosjektDeltagelseTimer);

                    break;

                case 10:
                    System.out.print("Prosjektid: ");
                    int timeForingProsjekt = in.nextInt();

                    System.out.print("Ansattid: ");
                    int timeForingAnsatt = in.nextInt();

                    System.out.print("Timer: ");
                    int timeForingTimer = in.nextInt();

                    prdetdao.oppdater(timeForingProsjekt, timeForingAnsatt, timeForingTimer);

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