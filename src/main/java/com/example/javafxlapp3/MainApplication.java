package com.example.javafxlapp3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Shape-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        ShapeController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("Draw Program");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}