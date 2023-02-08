package com.example.clinica.domain;

public class Sectie extends Entity<Integer>{
    String nume;
    Integer idSefDeSectie;
    Integer pretPerConsultatie;
    Integer durataMaxConsultatie;

    public Sectie(int i) {
        super(i);
    }

    public Sectie(Integer integer, String nume, int idSefDeSectie, int pretPerConsultatie, int durataMaxConsultatie) {
        super(integer);
        this.nume = nume;
        this.idSefDeSectie = idSefDeSectie;
        this.pretPerConsultatie = pretPerConsultatie;
        this.durataMaxConsultatie = durataMaxConsultatie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getIdSefDeSectie() {
        return idSefDeSectie;
    }

    public void setIdSefDeSectie(int idSefDeSectie) {
        this.idSefDeSectie = idSefDeSectie;
    }

    public int getPretPerConsultatie() {
        return pretPerConsultatie;
    }

    public void setPretPerConsultatie(int pretPerConsultatie) {
        this.pretPerConsultatie = pretPerConsultatie;
    }

    public int getDurataMaxConsultatie() {
        return durataMaxConsultatie;
    }

    public void setDurataMaxConsultatie(int durataMaxConsultatie) {
        this.durataMaxConsultatie = durataMaxConsultatie;
    }
}
