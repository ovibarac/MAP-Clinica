package com.example.clinica.controller;

import com.example.clinica.ClinicaApp;
import com.example.clinica.Service;
import com.example.clinica.domain.Sectie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class SectieController {
    Service service;
    public TableView<Sectie> sectiiTable;
    public TableColumn<Sectie, Integer> id;
    public TableColumn<Sectie,String> nume;
    public TableColumn<Sectie, Integer> idSefDeSectie;
    public TableColumn<Sectie, Integer> pretPerConsultatie;
    public TableColumn<Sectie, Integer> durataMaxConsultatie;

    ObservableList<Sectie> sectieModel = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nume.setCellValueFactory(new PropertyValueFactory<>("nume"));
        idSefDeSectie.setCellValueFactory(new PropertyValueFactory<>("idSefDeSectie"));
        pretPerConsultatie.setCellValueFactory(new PropertyValueFactory<>("pretPerConsultatie"));
        durataMaxConsultatie.setCellValueFactory(new PropertyValueFactory<>("durataMaxConsultatie"));

        sectiiTable.setItems(sectieModel);


        sectiiTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Stage consStage = new Stage();
            FXMLLoader consLoader = new FXMLLoader(ClinicaApp.class.getResource("consultatie-view.fxml"));
            Scene consScene = null;
            try {
                consScene = new Scene(consLoader.load(), 500, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            consStage.setTitle(newSelection.getNume());
            consStage.setScene(consScene);
            ConsultatieController consController = consLoader.getController();
            consController.setIdSectie(newSelection.getId());
            consController.setService(service);

            consStage.show();
        });
    }

    public void setService(Service service){
        this.service=service;
        //observer
        initModel();
    }

    void initModel(){
        sectieModel.setAll(service.allSectii());
    }



}
