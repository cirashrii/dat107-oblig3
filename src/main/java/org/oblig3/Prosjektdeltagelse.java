package org.oblig3;

import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3")
public class Prosjektdeltagelse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ansattId")
    private Ansatt ansatt;

    @ManyToOne
    @JoinColumn(name = "prosjektId")
    private Prosjekt prosjekt;

    private int timer;

    public Prosjektdeltagelse(Prosjekt prosjekt, Ansatt ansatt, int timer){
        this.prosjekt = prosjekt;
        this.ansatt = ansatt;
        this.timer = timer;
    }

    public Prosjektdeltagelse(){}

    public Prosjekt getProsjekt() {return prosjekt;}

    public void setProsjekt(Prosjekt prosjekt) {this.prosjekt = prosjekt;}

    public Ansatt getAnsatt() {return ansatt;}

    public void setAnsatt(Ansatt ansatt) {this.ansatt = ansatt;}

    public int getTimer() {return timer;}

    public void setTimer(int timer) {this.timer = timer;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    @Override
    public String toString() {
        return "Prosjektdeltagelse[" +
                "ansatt= " +  ansatt.getAnsattid() +
                ", stilling=" + ansatt.getStilling() +
                ", timer=" + timer +
                ']';
    }
}
