module com.example.proyecto_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;


    opens com.example.proyecto_3 to javafx.fxml;
    exports com.example.proyecto_3;
}