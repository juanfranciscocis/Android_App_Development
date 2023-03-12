package com.example.proyecto_3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CourseDB {
    //PROPERTIES
    final String DATABASE_URL = "jdbc:derby:Registros;create=false";
    final String COURSE_TABLE_QUERY =
            "SELECT courseID, courseName, facultyID FROM courses";






    //CONSTRUCTOR
    public CourseDB() {

        // AGREGANDO DATA AL FACULTY TABLE
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)){
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO courses (facultyID,courseName,courseID) VALUES  ('A52990', 'Data Structures', 'CSC-132A'),\n" +
                    "    ('A77587', 'Programming 2', 'CSC-12'),\n" +
                    "    ('B66750', 'Data Structures', 'CSC-123A'),\n" +
                    "    ('H99118', 'Intro to Programming', 'CSC-456A'),\n" +
                    "    ('H99118', 'Algorithms and Structures', 'CSC-132B'),\n" +
                    "    ('H99118', 'Programming 1', 'CSC-135N'),\n" +
                    "    ('J33486', 'Intro to Programming', 'CSC-234'),\n" +
                    "    ('A52990', 'Programming 1', 'CSC-2345D')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    //METHODS

    public ObservableList<Course> getCourseTable() {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(COURSE_TABLE_QUERY)) {

            ObservableList<Course> coursesList = FXCollections.observableArrayList();

            // Agregar cada registro a la lista observable de Faculty
            while (resultSet.next()) {
                String id = resultSet.getString("courseID");
                String name = resultSet.getString("courseName");
                String office = resultSet.getString("facultyID");
                coursesList.add(new Course(id, name, office));
            }

            return coursesList;


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return null;
    }

    public void addCourse(String courseID, String courseName, String facultyID) {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {
            statement.execute("INSERT INTO courses (courseID, courseName, facultyID) VALUES ('" + courseID + "', '" + courseName + "', '" + facultyID + "')");
            printInConsoleDB();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
    }

    public void editCourse(String courseID, String courseName, String facultyID) {
        String sql = "UPDATE courses SET courseID = ?, courseName = ? WHERE facultyID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, courseID);
            statement.setString(2, courseName);
            statement.setString(3, facultyID);
            statement.executeUpdate();
            printInConsoleDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet deleteCourse(String courseID) {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {


            String DELETE_COURSE = "DELETE FROM course WHERE courseID = " + courseID;
            ResultSet resultSet = statement.executeQuery(DELETE_COURSE);
            return resultSet;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }

        return null;
    }


    public ResultSet palabarasComunes(String palabra){
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {

            String PALABRAS_COMUNES = "SELECT * FROM course WHERE course LIKE '%" + palabra + "%'";
            ResultSet resultSet = statement.executeQuery(PALABRAS_COMUNES);
            return resultSet;

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return null;
    }


    private void printInConsoleDB() {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(COURSE_TABLE_QUERY)) {

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
