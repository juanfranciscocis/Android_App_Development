package com.example.proyecto_3;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class CursosXFacultyController {

    public ObservableList<Course> courseList;

    @FXML
    private TableView<Course> table_view_cursosxfaculty;

    public CursosXFacultyController(ObservableList<Course> courseList){
        this.courseList = courseList;
    }

    @FXML
    void initialize() throws SQLException {
        poblarTableView();
    }

    void poblarTableView() throws SQLException {

        try {
            table_view_cursosxfaculty.getColumns().clear();
            table_view_cursosxfaculty.getItems().clear();


        } catch (Exception e) {
            System.out.println("No se pudo limpiar la tabla");
            new AlertaErrorGUI("No se pudo limpiar la tabla");
        }

        try {
            TableColumn<Course, String> courseID = new TableColumn<>("ID");
            courseID.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<Course, String> courseName = new TableColumn<>("Name");
            courseName.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Course, String> courseFacultyID = new TableColumn<>("Faculty ID");
            courseFacultyID.setCellValueFactory(new PropertyValueFactory<>("facultyID"));
            table_view_cursosxfaculty.getColumns().addAll(courseID, courseName, courseFacultyID);
            table_view_cursosxfaculty.setItems(courseList);
        } catch (Exception e) {
            System.out.println("No se pudo poblar la tabla");
            new AlertaErrorGUI("No se pudo poblar la tabla");
        }



    }


}
