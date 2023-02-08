package com.example.clinica.controller;

import com.example.clinica.Service;
import com.example.clinica.domain.Consultatie;
import com.example.clinica.events.Event;
import com.example.clinica.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.Optional;

public class MedicController implements Observer<Event> {
    public TableView<Consultatie> tableView;
    public TableColumn<Consultatie, Integer> id;
    public TableColumn<Consultatie, String> cnp;
    public TableColumn<Consultatie, String> nume;
    public TableColumn<Consultatie, LocalDateTime> data;
    public TableView<Consultatie> sefTable;
    public TableColumn<Consultatie, Integer> id1;
    public TableColumn<Consultatie, String> cnp1;
    public TableColumn<Consultatie, String> nume1;
    public TableColumn<Consultatie, String> data1;

    ObservableList<Consultatie> model = FXCollections.observableArrayList();
    ObservableList<Consultatie> sefModel = FXCollections.observableArrayList();
    Service service;
    Integer idMedic;

    public void setIdMedic(Integer idMedic) {
        this.idMedic = idMedic;
    }

    public void initialize(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnp.setCellValueFactory(new PropertyValueFactory<>("CNPPacient"));
        nume.setCellValueFactory(new PropertyValueFactory<>("NumePacient"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableView.setItems(model);
        tableView.getSortOrder().add(data);

        sefTable.setDisable(true);
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnp1.setCellValueFactory(new PropertyValueFactory<>("CNPPacient"));
        nume1.setCellValueFactory(new PropertyValueFactory<>("NumePacient"));
        data1.setCellValueFactory(new PropertyValueFactory<>("data"));

        sefTable.setItems(sefModel);
        sefTable.getSortOrder().add(data);

    }

    private void initModel(){
        model.setAll(service.allConsultatii().stream().filter(x->x.getIdMedic()==idMedic && x.getData().compareTo(LocalDateTime.now())>0).toList());
        Optional<Integer> sectieId = service.getSectieOfSef(idMedic);
        if(sectieId.isPresent()){
            sefTable.setDisable(false);
            sefModel.setAll(service.allConsultatii().stream().filter(x->service.getMedic(x.getIdMedic()).get().getIdSectie()==sectieId.get()).toList());
        }
    }

    public void setService(Service service){
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @Override
    public void update(Event event) {
        initModel();
    }
}
