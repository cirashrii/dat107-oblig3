package org.oblig3;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(schema = "oblig3")
public class Prosjekt {
    @Id
    private int prosjektId;

    @OneToMany(mappedBy = "prosjekt", fetch = FetchType.EAGER)
    private List<Prosjektdeltagelse> prosjektdeltagelser;

    private String prosjektnavn;

    public Prosjekt(int prosjektId, String prosjektnavn){
        this.prosjektId = prosjektId;
        this.prosjektnavn = prosjektnavn;
    }

    public Prosjekt() {}

    public int getProsjektId() {return prosjektId;}

    public void setProsjektId(int prosjektId) {this.prosjektId = prosjektId;}

    public String getProsjektnavn() {return prosjektnavn;}

    public void setProsjektnavn(String prosjektnavn) {this.prosjektnavn = prosjektnavn;}

    public List<Prosjektdeltagelse> getProsjektdeltagelser() {return prosjektdeltagelser;}

    @Override
    public String toString() {
        return "Prosjekt[" +
                "prosjektId=" + prosjektId +
                ", prosjektnavn='" + prosjektnavn +
                ']';
    }
}