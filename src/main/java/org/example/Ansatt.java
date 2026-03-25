package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(schema = "oblig3")
public class Ansatt {

    @Id
    private int ansattid;
    private String brukernavn;
    private String fornavn;
    private String etternavn;
    private LocalDate ansettelsedato;
    private String stilling;
    private double maanedslonn;

    public Ansatt() {}

    public Ansatt(int ansattid, String brukernavn, String fornavn, String etternavn, LocalDate ansettelsedato, String stilling, double maanedslonn) {
        this.ansattid = ansattid;
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelsedato = ansettelsedato;
        this.stilling = stilling;
        this.maanedslonn = maanedslonn;
    }

    public String getAnsattid() { return brukernavn; }

    public void setAnsattid(int ansattid) { this.ansattid = ansattid; }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public LocalDate getAnsettelsedato() {
        return ansettelsedato;
    }

    public void setAnsettelsedato(LocalDate ansettelsedato) {
        this.ansettelsedato = ansettelsedato;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public double getMaanedslonn() {
        return maanedslonn;
    }

    public void setMaanedslonn(double maanedslonn) {
        this.maanedslonn = maanedslonn;
    }

    @Override
    public String toString() {
        return "Brukernavn: " + brukernavn + "\nFull navn: " + fornavn + " " + etternavn + "\nAnsattelsesdato: " + ansettelsedato + "\nStilling: " + stilling + "\nMånedslønn: " + maanedslonn + "\n";
    }
}
