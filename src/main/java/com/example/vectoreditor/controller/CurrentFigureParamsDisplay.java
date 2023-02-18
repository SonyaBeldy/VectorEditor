package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.Polyline;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CurrentFigureParamsDisplay implements Initializable {

    MainController mainController;

    public CurrentFigureParamsDisplay(MainController mainController) {
        this.mainController = mainController;
    }

    public void update() {
        updateRotateField();
        updateXPointField();
        updateYPointField();
        updateWidth();
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

    private void updateXPointField() {
        if(mainController.getCanvasController().getCurrentFigure() != null) {
            mainController.getXPointField().setText(String.valueOf(mainController.getCanvasController().getCurrentFigure().getCenter().getX()));
        }
    }
    private void updateYPointField() {
        if(mainController.getCanvasController().getCurrentFigure() != null) {
            mainController.getYPointField().setText(String.valueOf(mainController.getCanvasController().getCurrentFigure().getCenter().getY()));
        }
    }

    private void updateWidth() {
        //Optional<Figure> curFigure = Optional.empty();
//        curFigure = Optional.of(new Polyline(Color.AQUAMARINE));



        //mainController.getWidthField().setText(String.valueOf(mainController.getCanvasController().getCurrentFigure().getWidth()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
