package com.example.proyecto_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:derby:Registros;create=true");
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE faculty");
        statement.execute("DROP TABLE courses");
        statement.execute("CREATE TABLE faculty (facultyID INT, facultyName VARCHAR(50), office VARCHAR(50))");
        statement.execute("CREATE TABLE courses (courseID INT, courseName VARCHAR(50), facultyID INT)");

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("facultyGUI.fxml"));
        fxmlLoader.setController(new FacultyController());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Faculty GUI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}