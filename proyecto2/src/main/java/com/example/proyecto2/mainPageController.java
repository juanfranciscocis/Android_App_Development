package com.example.proyecto2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class mainPageController {

    @FXML
    private Button botonCalcular;

    @FXML
    private TextField factorialUsuario;

    @FXML
    private TableView<?> tablaResultadoSerial;

    @FXML
    private TableView<?> tablaResultadoTask;

    @FXML
    private TextField tasksUsuario;

    @FXML
    void buttonPressed(ActionEvent event) {
        int numeroFactorial = Integer.parseInt(factorialUsuario.getText());
        int numeroTasks = Integer.parseInt(tasksUsuario.getText());

        System.out.println("Factorial: " + numeroFactorial + " Tasks: " + numeroTasks);


    }

}
