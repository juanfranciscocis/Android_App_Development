package com.example.proyecto_3;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursosXFacultyController {

    public ResultSet resultSet;

    @FXML
    private TableView<String> table_view_cursosxfaculty;

    public CursosXFacultyController(ResultSet resultSet){
        this.resultSet = resultSet;
    }

    @FXML
    void initialize() throws SQLException {
        poblar();
    }

    void poblar() throws SQLException {

        //Delete all columns
        table_view_cursosxfaculty.getColumns().clear();
        //Delete all rows
        table_view_cursosxfaculty.getItems().clear();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        for (int i = 1; i <= numberOfColumns; i++) {
            TableColumn column = new TableColumn(metaData.getColumnName(i));
            column.setCellValueFactory(new PropertyValueFactory<>(metaData.getColumnName(i)));
            table_view_cursosxfaculty.getColumns().add(column);
        }



        while (resultSet.next()) {
            ArrayList<String> row = new ArrayList<>();
            for (int i = 1; i <= numberOfColumns; i++) {
                row.add((String) resultSet.getObject(i));
            }
            table_view_cursosxfaculty.getItems().add(String.valueOf(row));
        }

    }


}
