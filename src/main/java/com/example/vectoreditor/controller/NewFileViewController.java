package com.example.vectoreditor.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class NewFileViewController {
    private MainController mainController;

    public void init (MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void createBtnClick() {
        createNewView();
    }

    @FXML
    private void closeBtnClick() {

    }

    public void createNewView() {
        mainController.createNewCanvasView();
    }

}
