package com.example.vectoreditor.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class NewFileViewController {
    private TabPane workspaceBox;
    private MainController mainController;
    @FXML
    private Button createBtn;

    public void init (MainController mainController) {
        this.mainController = mainController;
        workspaceBox = mainController.getWorkspaceBox();
    }

    @FXML
    private void createBtnClick() {
        createNewView();
    }

    @FXML
    private void closeBtnClick() {

    }

    private void createNewView() {
        Tab newView = new Tab();
        newView.setText("Untitled " + workspaceBox.getTabs().size());


        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/vectoreditor/canvas-view.fxml"));
        ScrollPane scrollPane;

        try {
            scrollPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newView.setContent(scrollPane);
        workspaceBox.getTabs().add(newView);
        ScrollPaneController scrollPaneController = fxmlLoader.getController();

        scrollPaneController.init(mainController);


        newView.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mainController.setCurrentCanvasController(scrollPaneController);
                mainController.changeCanvas(scrollPaneController.getLayerBox());
            }
        });

    }

}
