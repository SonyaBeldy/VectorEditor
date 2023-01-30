package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class CurrentFigureParamsDisplay implements Initializable {

    @FXML
    private TextField xPointField;
    @FXML
    private TextField yPointField;
    @FXML
    private TextField widthField;

    @FXML
    private TextField heightField;
    private MainController mainController;
    private Figure currentFigure;



    public CurrentFigureParamsDisplay(MainController mainController) {
        this.mainController = mainController;

    }
    public void update() {
        currentFigure = mainController.getCanvasController().getCurrentFigure();
        if(currentFigure != null) {
            setRotateField();
        }
    }

    public void setRotateField() {
        if(currentFigure != null) {
            double angle = Math.round(Math.toDegrees(currentFigure.getAngle()));
//            if (angle < 0) {
//                angle += 360;
//            }
            mainController.getRotate().setText(String.valueOf(angle));
        }
    }

    private void setWidth(Figure currentFigure) {
        widthField.setText(String.valueOf(currentFigure.getWidth()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
