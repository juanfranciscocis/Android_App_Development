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
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {
            statement.execute("INSERT INTO faculty (facultyID, facultyName, office) VALUES ('" + facultyID + "', '" + facultyName + "', '" + office + "')");
            printInConsoleDB();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
    }

    public void editFaculty(String facultyID, String facultyName, String office) {
        String sql = "UPDATE faculty SET facultyName = ?, office = ? WHERE facultyID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, facultyName);
            statement.setString(2, office);
            statement.setString(3, facultyID);
            statement.executeUpdate();
            printInConsoleDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteFaculty(String facultyID) {

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
            e.printStackTrace();
        }


    }

    public ObservableList<Course> cursosPorFaculty(String facultyName) {
        String facultyIDQuery = "SELECT facultyID FROM faculty WHERE facultyName = ?";
        String cursosPorFacultyQuery = "SELECT course FROM course WHERE facultyID = ?";

        ObservableList<Course> courses = FXCollections.observableArrayList();

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement facultyIDStatement = connection.prepareStatement(facultyIDQuery);
                PreparedStatement cursosPorFacultyStatement = connection.prepareStatement(cursosPorFacultyQuery);
        ) {
            // Get faculty ID for given faculty name
            facultyIDStatement.setString(1, facultyName);
            ResultSet resultSet1 = facultyIDStatement.executeQuery();
            String facultyID = resultSet1.getString("facultyID");

            // Get courses for the faculty with the given ID
            cursosPorFacultyStatement.setString(1, facultyID);
            ResultSet resultSet2 = cursosPorFacultyStatement.executeQuery();

            // Process the results and add them to the list of courses
            while (resultSet2.next()) {
                String courseName = resultSet2.getString("course");
                String id = resultSet2.getString("courseID");
                courses.add(new Course(id,courseName, facultyID));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
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
            sqlException.printStackTrace();

        }
    }















}
