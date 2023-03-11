package com.example.proyecto_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver).newInstance();
        String protocol = "jdbc:derby:";
        Connection conn = DriverManager.getConnection(protocol + "MyDbTest;create=false");
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