package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.PointListUtils;
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
        updateHeight();
    }

    private void updateRotateField() {
        if(mainController.getCanvasController().getCurrentFigure().isPresent()) {
            Figure figure = mainController.getCanvasController().getCurrentFigure().get();
            double angle = Math.round(Math.toDegrees(figure.getAngle()));
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
        if(mainController.getCanvasController().getCurrentFigure().isPresent()) {
            Figure figure = mainController.getCanvasController().getCurrentFigure().get();
            mainController.getXPointField().setText(String.valueOf(figure.getCenter().getX()));
        }
    }
    private void updateYPointField() {
        if(mainController.getCanvasController().getCurrentFigure().isPresent()) {
            Figure figure = mainController.getCanvasController().getCurrentFigure().get();
            mainController.getYPointField().setText(String.valueOf(figure.getCenter().getY()));
        }
    }

    private void updateWidth() {
        if(mainController.getCanvasController().getCurrentFigure().isPresent()) {
            Figure figure = mainController.getCanvasController().getCurrentFigure().get();
            double width = PointListUtils.calcDist(figure.getBoardsPoints().get(0), figure.getBoardsPoints().get(1));
            mainController.getWidthField().setText(String.valueOf(width));
        }
    }

    private void updateHeight() {
        if(mainController.getCanvasController().getCurrentFigure().isPresent()) {
            Figure figure = mainController.getCanvasController().getCurrentFigure().get();
            double height = PointListUtils.calcDist(figure.getBoardsPoints().get(0), figure.getBoardsPoints().get(3));
            mainController.getHeightField().setText(String.valueOf(height));
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
