package com.example.proyecto3_1;
// Fig. 24.23: DisplayAuthors.java
// Displaying the contents of the Authors table.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Load the Derby embedded driver

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:derby:myDatabase;create=true");

            // Create a statement object
            stmt = conn.createStatement();

            // Create a table
            stmt.execute("CREATE TABLE myTable (id INT, name VARCHAR(50))");

            System.out.println("Database and table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the statement and connection
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
