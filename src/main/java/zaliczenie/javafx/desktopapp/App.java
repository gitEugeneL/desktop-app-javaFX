package zaliczenie.javafx.desktopapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),760, 640);
        String cssFile = App.class.getResource("css/style.css").toExternalForm();
        scene.getStylesheets().add(cssFile);
        stage.setTitle("Podstawy programowania");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}