package com.example.proyecto_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class CourseController {

    @FXML
    private TextField course_id;

    @FXML
    private TextField course_name;

    @FXML
    private TableView<?> course_tableview;


    @FXML
    private TextField cursosComunes;

    @FXML
    private TextField faculty_course_id;

    @FXML
    private TextField faculty_course_id1;

    public Course course = new Course();

    @FXML
    void agregarCurso(ActionEvent event) {

    }

    @FXML
    void editarCurso(ActionEvent event) {

    }

    @FXML
    void eliminarCurso(ActionEvent event) {

    }

    @FXML
    void palabrasComunesCursos(ActionEvent event) throws IOException {
        ResultSet resultSet = course.palabarasComunes(cursosComunes.getText());

        Stage stage = new Stage();
        FXMLLoader fxmlCoincheckerMenu = new FXMLLoader(Main.class.getResource("cursosXFacultyGUI.fxml"));
        fxmlCoincheckerMenu.setController(new CursosXFacultyController(resultSet));
        Scene scene = new Scene(fxmlCoincheckerMenu.load());
        stage.setTitle("Cursos Comunes");
        stage.setScene(scene);
        stage.show();

    }

}