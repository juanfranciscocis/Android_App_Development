package com.example.proyecto_3;


import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursosXFacultyController {

    public ObservableList<Course> courseList;

    @FXML
    private TableView<String> table_view_cursosxfaculty;

    public CursosXFacultyController(ObservableList<Course> courseList){
        this.courseList = courseList;
    }

    @FXML
    void initialize() throws SQLException {
        poblar();
    }

    void poblar() throws SQLException {



    }


}
