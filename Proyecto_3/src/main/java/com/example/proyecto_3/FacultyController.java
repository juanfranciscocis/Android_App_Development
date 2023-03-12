package com.example.proyecto_3;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FacultyController {

    private boolean coursesOpened = false;


    @FXML
    private TextField cursosPorFacultyName;

    @FXML
    private TextField faculty_id;

    @FXML
    private TextField faculty_id_delete;

    @FXML
    private TextField faculty_name;

    @FXML
    private TextField faculty_office;

    @FXML
    private TableView<Faculty> faculty_tableview;

    FacultyDB facultyDB = new FacultyDB();

    public FacultyController() throws SQLException {
    }


    void facultyList() throws SQLException {

        try {
            faculty_tableview.getColumns().clear();
            faculty_tableview.getItems().clear();

        }catch (Exception e) {
            System.out.println("No se pudo limpiar la tabla");
            new AlertaErrorGUI("No se pudo limpiar la tabla");


        }


        try {
            TableColumn<Faculty, String> facultyID = new TableColumn<>("ID");
            facultyID.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Faculty, String> facultyName = new TableColumn<>("Name");
            facultyName.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Faculty, String> facultyOffice = new TableColumn<>("Office");
            facultyOffice.setCellValueFactory(new PropertyValueFactory<>("office"));
            faculty_tableview.getColumns().addAll(facultyID, facultyName, facultyOffice);
            faculty_tableview.setItems(facultyDB.getFacultyTable());

        }catch (Exception e) {
            System.out.println("No se pudo llenar la tabla");
            new AlertaErrorGUI("No se pudo llenar la tabla");
        }





    }

    @FXML
    void  initialize() {
        try {
            System.out.println("FacultyController initialize");
            facultyList();
        } catch (SQLException throwables) {
            new AlertaErrorGUI("No se pudo llenar la tabla");
        }
    }

    @FXML
    void agregarFaculty(ActionEvent event) {
        facultyDB.addFaculty(faculty_id.getText(), faculty_name.getText(), faculty_office.getText());
        //Delete all rows and columns
        faculty_tableview.getColumns().clear();
        faculty_tableview.getItems().clear();

        System.out.println("Agregado");
        try {
            facultyList();
        } catch (SQLException throwables) {
            new AlertaErrorGUI("No se pudo llenar la tabla");
        }

    }

    @FXML
    void editarFaculty(ActionEvent event) {

        facultyDB.editFaculty(faculty_id.getText(), faculty_name.getText(), faculty_office.getText());
        System.out.println("Editado");
        try {
            facultyList();
        } catch (SQLException throwables) {
            new AlertaErrorGUI("No se pudo llenar la tabla");
        }

    }

    @FXML
    void eliminarFaculty(ActionEvent event) {

        if(!this.coursesOpened){
            new AlertaErrorGUI("No se puede eliminar un faculty hasta que no se abra la ventana de cursos (POR SU SEGURIDAD)");
            return;
        }

        // POP UP CONFIRMATION
        //Alerta de Confirmacion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Poniendo nombre Alerta
        alert.setTitle("Alerta");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType type2 = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        //Poniendo texto de confirmacion
        alert.setContentText("Quiere eliminar el registro? Si lo hace, se eliminaran todos los cursos asociados a este faculty");
        //Agregando boton al GUI
        //Mostrando Alerta
        alert.showAndWait();

        //Si el usuario presiona el boton de cancelar, no se elimina el registro
        if (alert.getResult()==ButtonType.CANCEL) {
            return;
        }


        facultyDB.deleteFaculty(faculty_id_delete.getText());
        //Delete all rows and columns
        faculty_tableview.getColumns().clear();
        faculty_tableview.getItems().clear();
        try {
            facultyList();
        } catch (SQLException throwables) {
            new AlertaErrorGUI("No se pudo llenar la tabla");
        }
    }

    @FXML
    void openCourse(ActionEvent event) throws IOException {

        this.coursesOpened = true;

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

        if (!this.coursesOpened) {
            System.out.println("Primero abra la ventana de cursos para que pueda buscar registros validos");

            //Alerta de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            //Poniendo nombre Alerta
            alert.setTitle("Error");
            //Poniendo texto de confirmacion
            alert.setContentText("Primero abra la ventana de cursos para que pueda buscar registros validos");
            //Agregando boton al GUI
            //Mostrando Alerta
            alert.showAndWait();
            return;
        }






        ObservableList<Course> courseList = facultyDB.getCoursesByFaculty(cursosPorFacultyName.getText());


        Stage stage = new Stage();
        FXMLLoader fxmlCoincheckerMenu = new FXMLLoader(Main.class.getResource("cursosXFacultyGUI.fxml"));
        fxmlCoincheckerMenu.setController(new CursosXFacultyController(courseList));
        Scene scene = new Scene(fxmlCoincheckerMenu.load());
        stage.setTitle("Cursos X Faculty GUI");
        stage.setScene(scene);
        stage.show();








    }



}
