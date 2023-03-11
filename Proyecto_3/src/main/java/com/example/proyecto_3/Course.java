package com.example.proyecto_3;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {
    //PROPERTIES
    final String DATABASE_URL = "jdbc:derby:register";
    final String COURSE_TABLE_QUERY =
            "SELECT courseID, course, facultyID FROM course";






    //CONSTRUCTOR
    public Course() {
    }

    //METHODS

    public ResultSet getCourseTable() {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(COURSE_TABLE_QUERY)) {

            return resultSet;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return null;
    }

    public ResultSet addCourse(String courseID, String course, String facultyID) {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {


            String ADD_COURSE = "INSERT INTO course (courseID, course, facultyID) VALUES (" + courseID + ", " + course + ", " + facultyID + ")";
            ResultSet resultSet = statement.executeQuery(ADD_COURSE);
            return resultSet;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return null;
    }

    public ResultSet editCourse(String courseID, String course, String facultyID) {
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);
                Statement statement = connection.createStatement();) {

            String EDIT_COURSE = "UPDATE course SET courseID = " + courseID + ", course = " + course + " WHERE facultyID = " + facultyID;
            ResultSet resultSet = statement.executeQuery(EDIT_COURSE);
            return resultSet;


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }

        return null;
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

















}
