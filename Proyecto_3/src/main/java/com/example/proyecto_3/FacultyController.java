package com.example.proyecto_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class FacultyController {


    @FXML
    private TextField cursosPorFacultyName;

    @FXML
    private TextField faculty_id;

    @FXML
    private TextField faculty_id1;

    @FXML
    private TextField faculty_name;

    @FXML
    private TextField faculty_office;

    @FXML
    private TableView<String> faculty_tableview;

    Faculty faculty = new Faculty();


    void facultyList() throws SQLException {
        ResultSet resultSet = faculty.getFacultyTable();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        for (int i = 1; i <= numberOfColumns; i++) {
            TableColumn column = new TableColumn(metaData.getColumnName(i));
            column.setCellValueFactory(new PropertyValueFactory<>(metaData.getColumnName(i)));
            faculty_tableview.getColumns().add(column);
        }



        while (resultSet.next()) {
            ArrayList<String> row = new ArrayList<>();
            for (int i = 1; i <= numberOfColumns; i++) {
                row.add((String) resultSet.getObject(i));
            }
            faculty_tableview.getItems().add(String.valueOf(row));
        }
    }

    //@FXML
    void  initialize() {
        try {
            facultyList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void agregarFaculty(ActionEvent event) {
        ResultSet resultSet = faculty.addFaculty(faculty_id.getText(), faculty_name.getText(), faculty_office.getText());
        //Delete all rows and columns
        faculty_tableview.getColumns().clear();
        faculty_tableview.getItems().clear();
        try {
            facultyList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void editarFaculty(ActionEvent event) {
        ResultSet resultSet = faculty.editFaculty(faculty_id.getText(), faculty_name.getText(), faculty_office.getText());
        //Delete all rows and columns
        faculty_tableview.getColumns().clear();
        faculty_tableview.getItems().clear();
        try {
            facultyList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void eliminarFaculty(ActionEvent event) {
        // POP UP CONFIRMATION
        //Alerta de Confirmacion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Poniendo nombre Alerta
        alert.setTitle("Alerta");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType type2 = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        //Poniendo texto de confirmacion
        alert.setContentText("Quiere eliminar el registro?");
        //Agregando boton al GUI
        //Mostrando Alerta
        alert.showAndWait();

        //Si el usuario presiona el boton de cancelar, no se elimina el registro
        if (alert.getResult()==ButtonType.CANCEL) {
            return;
        }


        ResultSet resultSet = faculty.deleteFaculty(faculty_id.getText());
        //Delete all rows and columns
        faculty_tableview.getColumns().clear();
        faculty_tableview.getItems().clear();
        try {
            facultyList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void openCourse(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlCoincheckerMenu = new FXMLLoader(Main.class.getResource("courseGUI.fxml"));
        fxmlCoincheckerMenu.setController(new CourseController());
        Scene scene = new Scene(fxmlCoincheckerMenu.load());
        stage.setTitle("Course GUI");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void bucar(ActionEvent event) throws IOException {
        ResultSet resultSet = faculty.cursosPorFaculty(cursosPorFacultyName.getText());

        Stage stage = new Stage();
        FXMLLoader fxmlCoincheckerMenu = new FXMLLoader(Main.class.getResource("cursosXFacultyGUI.fxml"));
        fxmlCoincheckerMenu.setController(new CursosXFacultyController(resultSet));
        Scene scene = new Scene(fxmlCoincheckerMenu.load());
        stage.setTitle("Cursos X Faculty GUI");
        stage.setScene(scene);
        stage.show();








    }



}
