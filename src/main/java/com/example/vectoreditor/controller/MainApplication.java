package com.example.vectoreditor.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/vectoreditor/canvas-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 550);
        stage.setTitle("Vector Editor");
        stage.setMinWidth(350);
        stage.setMinHeight(550);
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}