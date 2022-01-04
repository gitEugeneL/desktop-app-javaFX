module zaliczenie.javafx.desktopapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens zaliczenie.javafx.desktopapp to javafx.fxml;
    exports zaliczenie.javafx.desktopapp;
    exports zaliczenie.javafx.desktopapp.models;
    opens zaliczenie.javafx.desktopapp.models to javafx.fxml;
    exports zaliczenie.javafx.desktopapp.controllers;
    opens zaliczenie.javafx.desktopapp.controllers to javafx.fxml;
}