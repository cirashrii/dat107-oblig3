package org.oblig3;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(schema = "oblig3")
public class Prosjekt {
    @Id
    private int prosjektId;

    @ManyToMany
    @JoinTable (name = "prosjekt",
                joinColumns = @JoinColumn(name = "prosjektid"),
                inverseJoinColumns = @JoinColumn(name = "ansattid"))
    private List<Ansatt> ansatte;

    private String navn;
    private String beskrivelse;

    public Prosjekt(int prosjektId, String navn, String beskrivelse){
        this.prosjektId = prosjektId;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    public Prosjekt() {}

    public int getProsjektId() {return prosjektId;}

    public void setProsjektId(int prosjektId) {this.prosjektId = prosjektId;}

    public String getProsjektnavn() {return navn;}

    public void setProsjektnavn(String navn) {this.navn = navn;}

    public List<Ansatt> getAlleAnsatte() {return ansatte;}

    @Override
    public String toString() {
        return "Prosjekt[" +
                "prosjektId = " + prosjektId +
                ", navn = " + navn +
                ", beskrivelse = " + beskrivelse +
                ']';
    }
}