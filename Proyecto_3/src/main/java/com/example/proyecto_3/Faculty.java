package com.example.proyecto_3;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Faculty {


    //PROPERTIES
    final String DATABASE_URL = "jdbc:derby:register";
    final String FACULTY_TABLE_QUERY =
            "SELECT facultyID, facultyName, office FROM faculty";






    //CONSTRUCTOR
    public Faculty() {
    }

    //METHODS

    public ResultSet getFacultyTable() {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(FACULTY_TABLE_QUERY)) {

            return resultSet;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return null;
    }

    public ResultSet addFaculty(String facultyID, String facultyName, String office) {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {


            String ADD_FACULTY = "INSERT INTO faculty (facultyID, facultyName, office) VALUES (" + facultyID + ", " + facultyName + ", " + office + ")";
            ResultSet resultSet = statement.executeQuery(ADD_FACULTY);
            return resultSet;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return null;
    }

    public ResultSet editFaculty(String facultyID, String facultyName, String office) {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {

            String EDIT_FACULTY = "UPDATE faculty SET facultyName = " + facultyName + ", office = " + office + " WHERE facultyID = " + facultyID;
            ResultSet resultSet = statement.executeQuery(EDIT_FACULTY);
            return resultSet;


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }

        return null;
    }

    public ResultSet deleteFaculty(String facultyID) {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {

            String DELETE_FACULTY = "DELETE FROM faculty WHERE facultyID = " + facultyID;
            String DELETE_COURSE = "DELETE FROM course WHERE facultyID = " + facultyID;
            ResultSet resultSet = statement.executeQuery(DELETE_FACULTY);
            ResultSet resultSet1 = statement.executeQuery(DELETE_COURSE);
            return resultSet;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }

        return null;
    }

    public ResultSet cursosPorFaculty(String facultyName){
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {

            String FACULTY_ID = "SELECT facultyID FROM faculty WHERE facultyName = " + facultyName;
            ResultSet resultSet1 = statement.executeQuery(FACULTY_ID);
            String facultyID = resultSet1.getObject(1).toString();

            String CURSOS_POR_FACULTY = "SELECT course FROM course WHERE facultyID = " + facultyID;
            ResultSet resultSet = statement.executeQuery(CURSOS_POR_FACULTY);
            return resultSet;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }

        return null;
    }















}
