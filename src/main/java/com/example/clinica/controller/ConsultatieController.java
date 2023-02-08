package com.example.clinica.controller;

import com.example.clinica.Service;
import com.example.clinica.domain.Medic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.io.Console;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ConsultatieController {
    public TextField cnp;
    public TextField nume;
    public DatePicker data;
    public Spinner<Integer> ora;
    public ComboBox<String> medic;
    public Button addBtn;
    public TextField idcons;
    public Button deleteBtn;
    Service service;
    Integer idSectie;

    public void setService(Service service) {
        this.service=service;
        init();
    }

    public void setIdSectie(Integer idSectie) {
        this.idSectie = idSectie;
    }

    public void initialize(){

    }

    void init(){
        ora.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        8,20
                )
        );
        ObservableList<String> medici = FXCollections.observableArrayList(
                service.allMedici().stream().filter(x->x.getIdSectie()==idSectie).map(Medic::getNume).toList()
        );

        medic.getItems().addAll(medici);
    }

    public void handleAddConsultatie(){
        String cnpString = cnp.getText();
        String numeString = nume.getText();
        LocalDateTime dateTime = data.getValue().atTime(ora.getValue(), 0);
        Integer idMedic;
        try{
            if(service.findMedicByNume(medic.getValue()).isPresent()){
                //daca exista consultatie inceputa nu se poate progr
                idMedic= service.findMedicByNume(medic.getValue()).get().getId();
                service.addConsultatie(0, idMedic, cnpString, numeString, dateTime);
            }else{
                MessageAlert.showErrorMessage(null, "Medic not found");
            }
        }catch(Exception e){
            MessageAlert.showErrorMessage(null, e.getMessage());
        }


        //observer
    }

    public void handleDeleteConsultatie(){
        try{
            Integer id = Integer.parseInt(idcons.getText());
            service.deleteConsultatie(id);
        }catch(Exception e){
            MessageAlert.showErrorMessage(null, e.getMessage());
        }

    }
}
