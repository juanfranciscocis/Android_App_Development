package com.example.proyecto_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseController {

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

    @FXML
    private TextField faculty_course_id1;

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

    }

    @FXML
    void palabrasComunesCursos(ActionEvent event) throws IOException {
        ResultSet resultSet = courseDB.palabarasComunes(cursosComunes.getText());

        Stage stage = new Stage();
        FXMLLoader fxmlCoincheckerMenu = new FXMLLoader(Main.class.getResource("cursosXFacultyGUI.fxml"));
        //fxmlCoincheckerMenu.setController(new CursosXFacultyController(resultSet));
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