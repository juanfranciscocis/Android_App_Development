package com.example.proyecto_3;

import javafx.scene.control.Alert;

public class AlertaErrorGUI {

    AlertaErrorGUI(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



}
