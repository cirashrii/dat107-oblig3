package org.oblig3;

import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3")
@IdClass(ProsjektdeltagelsePK.class)
public class Prosjektdeltagelse {
    @Id
    @ManyToOne
    @JoinColumn(name = "ansattId")
    private Ansatt ansatt;

    @Id
    @ManyToOne
    @JoinColumn(name = "prosjektId")
    private Prosjekt prosjekt;

    private int timetall;
    private String rolle;

    public Prosjektdeltagelse(Prosjekt prosjekt, Ansatt ansatt, int timetall, String rolle){
        this.prosjekt = prosjekt;
        this.ansatt = ansatt;
        this.timetall = timetall;
        this.rolle = rolle;
    }

    public Prosjektdeltagelse(){}

    public Prosjekt getProsjekt() {return prosjekt;}

    public void setProsjekt(Prosjekt prosjekt) {this.prosjekt = prosjekt;}

    public Ansatt getAnsatt() {return ansatt;}

    public void setAnsatt(Ansatt ansatt) {this.ansatt = ansatt;}

    public int getTimer() {return timetall;}

    public void setTimer(int timer) {this.timetall = timetall;}

    public String getRolle() {return rolle;}

    public void setRolle(String rolle) {this.rolle = rolle;}

    @Override
    public String toString() {
        return "Prosjektdeltagelse[" +
                "ansatt = " +  ansatt.getAnsattid() +
                ", stilling = " + ansatt.getStilling() +
                ", timer = " + timetall +
                ", rolle = " + rolle +
                ']';
    }
}
