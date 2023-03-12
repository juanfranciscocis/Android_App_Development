package com.example.proyecto_3;

public class Course {
    private String id;
    private String name;
    private String facultyID;

    public Course(String id, String name, String facultyID) {
        this.id = id;
        this.name = name;
        this.facultyID = facultyID;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFacultyID() {
        return facultyID;
    }
}
