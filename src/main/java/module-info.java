module application.ndiaya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens application.ndiaya to javafx.fxml;
    exports application.ndiaya;
}