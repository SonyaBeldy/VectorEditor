package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.figure.Figure;
import com.example.vectoreditor.model.PointListUtils;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrentFigureParamsDisplay implements Initializable {

    MainController mainController;

    public CurrentFigureParamsDisplay(MainController mainController) {
        this.mainController = mainController;
    }
    public void update(CanvasViewController canvasController) {
        updateRotateField(canvasController);
        updateXPointField(canvasController);
        updateYPointField(canvasController);
        updateWidth(canvasController);
        updateHeight(canvasController);
    }
    public void update() {
        updateRotateField();
        updateXPointField();
        updateYPointField();
        updateWidth();
        updateHeight();
    }

    private void updateRotateField(CanvasViewController canvasController) {
        if(canvasController.getCurrentFigure().isPresent()) {
            Figure figure = canvasController.getCurrentFigure().get();
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
    private void updateXPointField(CanvasViewController canvasController) {
        if(canvasController.getCurrentFigure().isPresent()) {
            Figure figure = canvasController.getCurrentFigure().get();
            mainController.getXPointField().setText(String.valueOf(figure.getCenter().getX()));
        }
    }
    private void updateXPointField() {
        if(mainController.getCanvasController().getCurrentFigure().isPresent()) {
            Figure figure = mainController.getCanvasController().getCurrentFigure().get();
            mainController.getXPointField().setText(String.valueOf(figure.getCenter().getX()));
        }
    }
    private void updateYPointField(CanvasViewController canvasController) {
        if(canvasController.getCurrentFigure().isPresent()) {
            Figure figure = canvasController.getCurrentFigure().get();
            mainController.getYPointField().setText(String.valueOf(figure.getCenter().getY()));
        }
    }
    private void updateYPointField() {
        if(mainController.getCanvasController().getCurrentFigure().isPresent()) {
            Figure figure = mainController.getCanvasController().getCurrentFigure().get();
            mainController.getYPointField().setText(String.valueOf(figure.getCenter().getY()));
        }
    }

    private void updateWidth(CanvasViewController canvasController) {
        if(canvasController.getCurrentFigure().isPresent()) {
            Figure figure = canvasController.getCurrentFigure().get();
            double width = PointListUtils.calcDist(figure.getBoardsPoints().get(0), figure.getBoardsPoints().get(1));
            mainController.getWidthField().setText(String.valueOf(width));
        }
    }
    private void updateWidth() {
        if(mainController.getCanvasController().getCurrentFigure().isPresent()) {
            Figure figure = mainController.getCanvasController().getCurrentFigure().get();
            double width = PointListUtils.calcDist(figure.getBoardsPoints().get(0), figure.getBoardsPoints().get(1));
            mainController.getWidthField().setText(String.valueOf(width));
        }
    }

    private void updateHeight(CanvasViewController canvasController) {
        if(canvasController.getCurrentFigure().isPresent()) {
            Figure figure = canvasController.getCurrentFigure().get();
            double height = PointListUtils.calcDist(figure.getBoardsPoints().get(0), figure.getBoardsPoints().get(3));
            mainController.getHeightField().setText(String.valueOf(height));
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
