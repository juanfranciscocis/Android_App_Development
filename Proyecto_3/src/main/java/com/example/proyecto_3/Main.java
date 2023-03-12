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


        /*
        // EN CASO DE QUE LA BASE DE DATOS LA QUERRAMOS BORRAR
        try (Connection connection = DriverManager.getConnection("jdbc:derby:Registros;create=true")){
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE courses");
            statement.execute("DROP TABLE faculty");
            System.out.println("Tables dropped (MAIN)");
        }

         */


        try (Connection connection = DriverManager.getConnection("jdbc:derby:Registros;create=true")){
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE faculty (facultyID VARCHAR(6) NOT NULL , facultyName VARCHAR(50) NOT NULL , office VARCHAR(50) NOT NULL , PRIMARY KEY (facultyID))");
            statement.execute("CREATE TABLE courses (courseID VARCHAR(50) NOT NULL , courseName VARCHAR(50) NOT NULL , facultyID VARCHAR(6) NOT NULL , PRIMARY KEY (courseID), FOREIGN KEY (facultyID) REFERENCES faculty(facultyID))");
            System.out.println("Tables created (MAIN)");

        }catch (Exception e) {
            System.out.println("Tables already exist (MAIN)");
        }
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