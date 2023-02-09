package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrentFigureParamsDisplay implements Initializable {

    private TextField xPointField;
    private TextField yPointField;
    private TextField widthField;
    private TextField heightField;
    private TextField rotateField;
    private Figure currentFigure;

    MainController mainController;

    public CurrentFigureParamsDisplay(MainController mainController) {
        this.mainController = mainController;
    }

    public CurrentFigureParamsDisplay(
            TextField xPointField,
            TextField yPointField,
            TextField widthField,
            TextField heightField,
            TextField rotateField,
            Figure currentFigure

    ) {
        this.xPointField = xPointField;
        this.yPointField = yPointField;
        this.widthField = widthField;
        this.heightField = heightField;
        this.rotateField = rotateField;
        this.currentFigure = currentFigure;
    }
    public void update() {
        if(mainController.getCanvasController().getCurrentFigure() != null) {
            updateRotateField();
        }
    }

    private void updateRotateField() {
        if(mainController.getCanvasController().getCurrentFigure() != null) {
            double angle = Math.round(Math.toDegrees(mainController.getCanvasController().getCurrentFigure().getAngle()));
            if (angle < 0) {
                angle+= 360;
            }
            if (angle > 360) {
                angle-= 360;
            }
           mainController.getRotate().setText(String.valueOf(angle));
        }
    }

    private void updateWidth() {
        widthField.setText(String.valueOf(currentFigure.getWidth()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
