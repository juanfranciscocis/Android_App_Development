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
            System.out.println();
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
            new AlertaErrorGUI("Error al cargar la tabla de cursos");

        }
        return null;
    }

    public void addCourse(String courseID, String courseName, String facultyID) {

        if (courseID.isEmpty() || courseName.isEmpty() || facultyID.isEmpty()) {
            new AlertaErrorGUI("No se puede dejar campos vacios");
            return;
        }

        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {
            statement.execute("INSERT INTO courses (courseID, courseName, facultyID) VALUES ('" + courseID + "', '" + courseName + "', '" + facultyID + "')");
            printInConsoleDB();
        } catch (SQLException sqlException) {
            new AlertaErrorGUI("Error al agregar el curso, Verificar Datos");

        }
    }

    public void editCourse(String courseID, String courseName, String facultyID) {

        if (courseID.isEmpty() || courseName.isEmpty() || facultyID.isEmpty()) {
            new AlertaErrorGUI("No se puede dejar campos vacios");
            return;
        }

        String sql = "UPDATE courses SET facultyID = ?, courseName = ? WHERE courseID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, facultyID);
            statement.setString(2, courseName);
            statement.setString(3, courseID);
            statement.executeUpdate();
            if (statement.executeUpdate() == 0)
                new AlertaErrorGUI("No se encontro el curso");
            printInConsoleDB();
        } catch (SQLException e) {
            new AlertaErrorGUI("Error al editar el curso, Verificar Datos");
        }
    }


    public void deleteCourse(String courseID) {

        if (courseID.isEmpty()) {
            new AlertaErrorGUI("No se puede dejar campos vacios");
            return;
        }

        String sql = "DELETE FROM courses WHERE courseID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, courseID);
            statement.executeUpdate();
            printInConsoleDB();
        } catch (SQLException e) {
            new AlertaErrorGUI("Error al eliminar el curso, Verificar Datos");
        }


    }



    public ObservableList<Course> palabrasComunes(String palabra) {

        if (palabra.isEmpty()) {
            new AlertaErrorGUI("No se puede dejar campos vacios");
            return null;
        }

        ObservableList<Course> courses = FXCollections.observableArrayList();
        String sql = "SELECT * FROM courses WHERE courseName LIKE ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + palabra + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course(resultSet.getString("courseID"), resultSet.getString("courseName"),
                        resultSet.getString("facultyID"));
                courses.add(course);
            }
        } catch (SQLException e) {
            new AlertaErrorGUI("Error al buscar el curso, Verificar Datos");
        }
        return courses;
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
            System.out.println("Error al cargar la tabla de cursos");

        }
    }

















}
