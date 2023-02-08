package com.example.clinica;

import com.example.clinica.controller.MedicController;
import com.example.clinica.controller.SectieController;
import com.example.clinica.domain.Medic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClinicaApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Service service = new Service();

        FXMLLoader fxmlLoader = new FXMLLoader(ClinicaApp.class.getResource("sectie-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        stage.setTitle("Sectii");
        stage.setScene(scene);
        SectieController sectieController = fxmlLoader.getController();
        sectieController.setService(service);
        stage.show();

        service.allMedici().forEach(x->{
            Stage medicStage = new Stage();
            FXMLLoader medicLoader = new FXMLLoader(ClinicaApp.class.getResource("medic-view.fxml"));
            Scene medic = null;
            try {
                medic = new Scene(medicLoader.load(), 700, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            medicStage.setTitle("Medic "+x.getId() + ": " + x.getNume());
            medicStage.setScene(medic);
            MedicController medicController = medicLoader.getController();
            medicController.setIdMedic(x.getId());
            medicController.setService(service);
            medicStage.show();
        });

    }
}
