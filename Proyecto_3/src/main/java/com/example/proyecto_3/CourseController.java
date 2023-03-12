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

public class CourseController {


    @FXML
    private TextField course_delete;

    @FXML
    private TextField course_id;

    @FXML
    private TextField course_name;

    @FXML
    private TableView<Course> course_tableview;


    @FXML
    private TextField cursosComunes;

    @FXML
    private TextField faculty_course_id;



    public CourseDB courseDB = new CourseDB();

    @FXML
    void initialize() throws SQLException {
        try {
            System.out.println("CourseController initialize");
            courseList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    void courseList() throws SQLException {

        try {
            course_tableview.getColumns().clear();
            course_tableview.getItems().clear();

        }catch (Exception e) {
            System.out.println("No se pudo limpiar la tabla");


        }


        try {
            TableColumn<Course, String> courseID = new TableColumn<>("ID");
            courseID.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Course, String> courseName = new TableColumn<>("Name");
            courseName.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Course, String> facultyID = new TableColumn<>("FacultyID");
            facultyID.setCellValueFactory(new PropertyValueFactory<>("facultyID"));
            course_tableview.getColumns().addAll(courseID, courseName, facultyID);
            course_tableview.setItems(courseDB.getCourseTable());

        }catch (Exception e) {
            System.out.println("No se pudo llenar la tabla");
        }





    }





    @FXML
    void agregarCurso(ActionEvent event) {

        try {
            courseDB.addCourse(course_id.getText(), course_name.getText(), faculty_course_id.getText());
            courseList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @FXML
    void editarCurso(ActionEvent event) {

        try {
            courseDB.editCourse(course_id.getText(), course_name.getText(), faculty_course_id.getText());
            courseList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void eliminarCurso(ActionEvent event) {

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


        courseDB.deleteCourse(course_delete.getText());
        //Delete all rows and columns
        course_tableview.getColumns().clear();
        course_tableview.getItems().clear();
        try {
            courseList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void palabrasComunesCursos(ActionEvent event) throws IOException {
        ObservableList<Course> courses = courseDB.palabrasComunes(cursosComunes.getText());

        Stage stage = new Stage();
        FXMLLoader fxmlCoincheckerMenu = new FXMLLoader(Main.class.getResource("cursosXFacultyGUI.fxml"));
        fxmlCoincheckerMenu.setController(new CursosXFacultyController(courses));
        Scene scene = new Scene(fxmlCoincheckerMenu.load());
        stage.setTitle("Cursos Comunes");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void recargarDatos(ActionEvent event) throws SQLException {
        courseList();

    }

}