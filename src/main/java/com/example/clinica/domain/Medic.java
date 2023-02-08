package com.example.clinica.domain;

public class Medic extends Entity<Integer>{
    int idSectie;
    String nume;
    int vechime;
    boolean rezident;

    public Medic(Integer integer) {
        super(integer);
    }

    public Medic(Integer integer, int idSectie, String nume, int vechime, boolean rezident) {
        super(integer);
        this.idSectie = idSectie;
        this.nume = nume;
        this.vechime = vechime;
        this.rezident = rezident;
    }

    public int getIdSectie() {
        return idSectie;
    }

    public void setIdSectie(int idSectie) {
        this.idSectie = idSectie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVechime() {
        return vechime;
    }

    public void setVechime(int vechime) {
        this.vechime = vechime;
    }

    public boolean isRezident() {
        return rezident;
    }

    public void setRezident(boolean rezident) {
        this.rezident = rezident;
    }
}
