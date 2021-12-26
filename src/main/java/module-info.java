module zaliczenie.javafx.desktopapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens zaliczenie.javafx.desktopapp to javafx.fxml;
    exports zaliczenie.javafx.desktopapp;
}