package org.oblig3;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(schema = "oblig3")
public class Avdeling {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdelingid;
    private String navn;

    @OneToMany(mappedBy = "avdeling")
    private List<Ansatt> ansatte;

    @OneToOne
    @JoinColumn(name = "sjef")
    private Ansatt sjef;

    public Avdeling() {}

    public Avdeling(int avdelingid, String navn, Ansatt sjef, List<Ansatt> ansatte) {
        this.avdelingid = avdelingid;
        this.navn = navn;
        this.sjef = sjef;
        this.ansatte = ansatte;
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

    public Ansatt getSjef() {
        return sjef;
    }

    public void setSjef(Ansatt sjef) {
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
        return "\nAvdeling{" +
                "Avdelingid: " + avdelingid +
                "\nNavn: " + navn +
                "\nSjef: " + (sjef != null ? sjef.getFornavn() + " " + sjef.getEtternavn() : null) +
                "\nAntall ansatte i avdelingen: " + (ansatte != null ? ansatte.size() : 0) + "\n";
    }
}