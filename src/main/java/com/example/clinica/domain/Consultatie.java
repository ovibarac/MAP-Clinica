package com.example.clinica.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class Consultatie extends Entity<Integer>{
    Integer idMedic;
    String CNPPacient;
    String numePacient;
    LocalDateTime data;

    public Consultatie(Integer integer) {
        super(integer);
    }

    public Consultatie(Integer id, Integer idMedic, String CNPPacient, String numePacient, LocalDateTime data) {
        super(id);
        this.idMedic = idMedic;
        this.CNPPacient = CNPPacient;
        this.numePacient = numePacient;
        this.data = data;
    }

    public Integer getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(Integer idMedic) {
        this.idMedic = idMedic;
    }

    public String getCNPPacient() {
        return CNPPacient;
    }

    public void setCNPPacient(String CNPPacient) {
        this.CNPPacient = CNPPacient;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

}
