package org.oblig3;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(schema = "oblig3")
public class Avdeling {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdelingid;
    private String navn;
    private int sjef;

    @OneToMany(mappedBy = "avdeling",
            fetch = FetchType.EAGER)
    private List<Ansatt> ansatte;

    public Avdeling() {}

    public Avdeling(int avdelingid, String navn, int sjef) {
        this.avdelingid = avdelingid;
        this.navn = navn;
        this.sjef = sjef;
    }

    public int getAvdelingid() {
        return avdelingid;
    }

    public void setAvdelingid(int avdelingid) {
        this.avdelingid = avdelingid;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getSjef() {
        return sjef;
    }

    public void setSjef(int sjef) {
        this.sjef = sjef;
    }

    public List<Ansatt> getAnsatte() {
        return ansatte;
    }

    public void setAnsatte(List<Ansatt> ansatte) {
        this.ansatte = ansatte;
    }

    @Override
    public String toString() {
        return "Avdeling{" +
                "avdelingid=" + avdelingid +
                ", navn='" + navn + '\'' +
                ", sjef=" + sjef +
                ", ansatte=" + ansatte +
                '}';
    }
}