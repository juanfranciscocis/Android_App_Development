package com.example.proyecto_3;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class FacultyDB {


    //PROPERTIES
    final String DATABASE_URL = "jdbc:derby:Registros;create=false";
    final String FACULTY_TABLE_QUERY =
            "SELECT facultyID, facultyName, office FROM faculty";






    //CONSTRUCTOR
    public FacultyDB() throws SQLException {
        // AGREGANDO DATA AL FACULTY TABLE
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)){
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO faculty (facultyID, facultyName, office) VALUES     ('A52990', 'John Smith', 'MTC-218'),\n" +
                    "    ('A77587', 'Sally Smith', 'MTC-320'),\n" +
                    "    ('B66750', 'Bob Jones', 'MTC-257'),\n" +
                    "    ('B78880', 'Sue Jones', 'MTC-211'),\n" +
                    "    ('B86590', 'Fred Davis', 'MTC-214'),\n" +
                    "    ('H99118', 'Ying Bai', 'MTC-336'),\n" +
                    "    ('J33486', 'Steve Johnson', 'MTC-118'),\n" +
                    "    ('K69880', 'Jenney King', 'MTC-324')");
        }catch (SQLException e){
            System.out.println();
        }
    }

    //METHODS
    public ObservableList<Faculty> getFacultyTable() {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(FACULTY_TABLE_QUERY)) {

            ObservableList<Faculty> facultyList = FXCollections.observableArrayList();

            // Agregar cada registro a la lista observable de Faculty
            while (resultSet.next()) {
                String id = resultSet.getString("facultyID");
                String name = resultSet.getString("facultyName");
                String office = resultSet.getString("office");
                facultyList.add(new Faculty(id, name, office));
            }

            return facultyList;


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return null;
    }

    public void addFaculty(String facultyID, String facultyName, String office) {

        if (facultyID.isEmpty() || facultyName.isEmpty() || office.isEmpty()) {
            new AlertaErrorGUI("Error al agregar el registro, Datos Vacios");
            return;
        }

        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {
            statement.execute("INSERT INTO faculty (facultyID, facultyName, office) VALUES ('" + facultyID + "', '" + facultyName + "', '" + office + "')");
            printInConsoleDB();
        } catch (SQLException sqlException) {
            new AlertaErrorGUI("Error al agregar el registro, Verifique Datos");

        }
    }

    public void editFaculty(String facultyID, String facultyName, String office) {

        if (facultyID.isEmpty() || facultyName.isEmpty() || office.isEmpty()) {
            new AlertaErrorGUI("Error al editar el registro, Datos Vacios");
            return;
        }

        String sql = "UPDATE faculty SET facultyName = ?, office = ? WHERE facultyID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, facultyName);
            statement.setString(2, office);
            statement.setString(3, facultyID);
            statement.executeUpdate();
            if (statement.executeUpdate() == 0)
                new AlertaErrorGUI("Error al editar el registro, Verifique Datos");
            printInConsoleDB();
        } catch (SQLException e) {
            new AlertaErrorGUI("Error al editar el registro, Verifique Datos");
        }
    }


    public void deleteFaculty(String facultyID) {

        if (facultyID.isEmpty()) {
            new AlertaErrorGUI("Error al eliminar el registro, Datos Vacios");
            return;
        }

        //TODO: BORRAR TODOS LOS CURSOS QUE TENGA EL FACULTY
        String sql2 = "DELETE FROM courses WHERE facultyID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, facultyID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        String sql = "DELETE FROM faculty WHERE facultyID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, facultyID);
            statement.executeUpdate();
            printInConsoleDB();
        } catch (SQLException e) {
            new AlertaErrorGUI("Error al eliminar el registro, Verifique Datos");
        }


    }

    public ObservableList<Course> getCoursesByFaculty(String facultyName) {
        if (facultyName.isEmpty()) {
            new AlertaErrorGUI("Error al buscar los cursos, Datos Vacios");
            return null;
        }
        ObservableList<Course> courses = FXCollections.observableArrayList();
        String facultyID = "";
        String sql = "SELECT facultyID FROM faculty WHERE facultyName = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, facultyName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                facultyID = resultSet.getString("facultyID");
                System.out.println(facultyID);
            }
        } catch (SQLException e) {
            new AlertaErrorGUI("Error al buscar los cursos, Verifique Datos");
        }

        sql = "SELECT * FROM courses WHERE facultyID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, facultyID);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Course course = new Course(resultSet.getString("courseID"), resultSet.getString("courseName"),
                        resultSet.getString("facultyID"));
                courses.add(course);
            }
        } catch (SQLException e) {
            new AlertaErrorGUI("Error al buscar los cursos, Verifique Datos");
        }




        return courses;
    }







    private void printInConsoleDB() {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(FACULTY_TABLE_QUERY)) {

            // Columnas
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            System.out.println("Faculty Table of Registros Database:\n");
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            System.out.println();

            // Filas
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                System.out.println();
            }





        } catch (SQLException sqlException) {
            new AlertaErrorGUI("Error al imprimir la base de datos, en el terminal");

        }
    }















}
