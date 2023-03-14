package com.example.vectoreditor.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class NewFileViewController {
    private TabPane workspaceTabPane;
    private MainController mainController;

    public void init (MainController mainController) {
        this.mainController = mainController;
        workspaceTabPane = mainController.getWorkspaceBox();
    }

    @FXML
    private void createBtnClick() {
        createNewView();
    }

    @FXML
    private void closeBtnClick() {

    }

    public void createNewView() {
        Tab newView = new Tab();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/vectoreditor/canvas-view.fxml"));
        ScrollPane scrollPane;

        try {
            scrollPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newView.setContent(scrollPane);
        workspaceTabPane.getTabs().add(newView);
        newView.setText("Untitled " + workspaceTabPane.getTabs().size());

        CanvasViewController scrollPaneController = fxmlLoader.getController();
        scrollPaneController.init(mainController);
        mainController.changeLayerBox(scrollPaneController.getLayerBox());

        workspaceTabPane.getSelectionModel().select(newView);
        newView.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mainController.setCurrentCanvasController(scrollPaneController);
                mainController.changeLayerBox(scrollPaneController.getLayerBox());
            }
        });

    }

}
