package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.*;
import com.example.vectoreditor.model.figures.Figure;
import com.example.vectoreditor.model.figures.FigureDecorationData;
import com.example.vectoreditor.model.unused.SingleFrame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PropertiesBoxController implements Initializable {

    private FigureDecorationData properties;
    private CanvasViewController canvasViewController;
    @FXML
    private ColorPicker strokeColor;

    @FXML
    private ColorPicker fillColor;

    @FXML
    private TextField heightField;

    @FXML
    private TextField rotateField;

    @FXML
    private TextField widthField;

    @FXML
    private TextField xPointField;

    @FXML
    private TextField yPointField;


    public void init(CanvasViewController canvasViewController) {
        this.canvasViewController = canvasViewController;
        widthField.setText("1");
    }
    @FXML
    void chooseFillColor(ActionEvent event) {

    }

    @FXML
    void chooseStrokeColor(ActionEvent event) {
        if(canvasViewController.getSelectedFiguresList().isEmpty()) {
            return;
        }
        ///////////пока цвет по старому
        Figure figure = canvasViewController.getSelectedFiguresList().getLast().getFigure();
        figure.getFigureDecorationData().setStrokeColor(Optional.of(strokeColor.getValue()));
    }

    @FXML
    void move(ActionEvent event) {
        if(canvasViewController.getSelectedFiguresList().isEmpty()) {
            return;
        }
        ////////////////////
        Figure figure = canvasViewController.getSelectedFiguresList().getLast().getFigure();

        Point newCenter = new Point(Double.parseDouble(xPointField.getText()), Double.parseDouble(yPointField.getText()));
        Point center = figure.getCenter();
        Point shift = new Point(newCenter.getX() - center.getX(), newCenter.getY() - center.getY());
        figure.move(shift);
        canvasViewController.redrawAllFigures();
    }

    @FXML
    void rotate(ActionEvent event) {
        if(canvasViewController.getSelectedFiguresList().isEmpty()) {
            return;
        }

        double angle = Double.parseDouble(rotateField.getText());
        angle = Math.toRadians(angle);
        /////////////////////////
        Figure figure = canvasViewController.getSelectedFiguresList().getLast().getFigure();

        double angleDiff = angle - figure.getTransformProperties().getAngle();
        figure.rotate(figure, figure.getCenter(), angleDiff);
        figure.getTransformProperties().setAngle(angle);
        canvasViewController.redrawAllFigures();
    }


    public void initProperties(FigureDecorationData properties) {
        properties.setFillColor(Optional.of(fillColor.getValue()));
        properties.setStrokeColor(Optional.of(strokeColor.getValue()));
//        properties.setStrokeWidth();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillColor.setValue(Color.BLACK);
        strokeColor.setValue(Color.BLACK);
    }

    public FigureDecorationData getDecorationProperties() {
        FigureDecorationData figureDecorationData = new FigureDecorationData(Optional.of(strokeColor.getValue()),
                Optional.of(fillColor.getValue()), 1);
        return figureDecorationData;
    }

    public void update() {
        updateRotateField(canvasViewController.getSelectedFiguresList());
        updateXPointField(canvasViewController.getSelectedFiguresList());
        updateYPointField(canvasViewController.getSelectedFiguresList());
        updateWidth(canvasViewController.getSelectedFiguresList());
        updateHeight(canvasViewController.getSelectedFiguresList());
    }

    private void updateRotateField(SelectedFigureControllsList list) {
        if(list.isEmpty()) {
            rotateField.setText("");
            return;
        }
        System.out.println("figureItemController.get().getFigure().getTransformProperties().getAngle() " + list.getLast().getFigure().getTransformProperties().getAngle());
        double angle = Math.round(Math.toDegrees(list.getLast().getFigure().getTransformProperties().getAngle()));
        if (angle < 0) {
            angle+= 360;
        }
        if (angle > 360) {
            angle-= 360;
        }
        System.out.println(angle);
       rotateField.setText(String.valueOf(angle));
    }

    private void updateXPointField(SelectedFigureControllsList list) {
        if (list.isEmpty()) {
            xPointField.setText("");
        } else {
            xPointField.setText(String.valueOf(list.getLast().getFigure().getCenter().getX()));
        }
    }

    private void updateYPointField(SelectedFigureControllsList list) {
        if(list.isEmpty()) {
            yPointField.setText("");
        } else {
           yPointField.setText(String.valueOf(list.getLast().getFigure().getCenter().getY()));
        }
    }


    ////////////////////
    private void updateWidth(SelectedFigureControllsList list) {
        if(list.isEmpty()) {
            widthField.setText("");
        } else {
            Frame frame;
            if(list.isSingle()) {
                frame = new SingleFrame(list.getLast().getFigure());
            } else {
                frame = new GroupFrame(list.getAllFigures());
            }

            double width = PointListUtils.calcDist(frame.getEdgesPoints().get(0), frame.getEdgesPoints().get(1));
            widthField.setText(String.valueOf(width));
        }

    }


    ///////////////
    private void updateHeight(SelectedFigureControllsList list) {
        if(list.isEmpty()) {
            heightField.setText("");
        } else {
            Frame frame;
            if(list.isSingle()) {
                frame = new SingleFrame(list.getLast().getFigure());
            } else {
                frame = new GroupFrame(list.getAllFigures());
            }

            double height = PointListUtils.calcDist(frame.getEdgesPoints().get(0), frame.getEdgesPoints().get(3));
            heightField.setText(String.valueOf(height));
        }
    }

    public void getProperties() {

    }

    public void setCanvasViewController(CanvasViewController canvasViewController) {
        this.canvasViewController = canvasViewController;
    }


    public void setStrokeColor(Color color) {
        strokeColor.setValue(color);
    }

    public void setFillColor(Color color) {
        fillColor.setValue(color);
    }

    public Color getStrokeColor() {
        return strokeColor.getValue();
    }

    public Color getFillColor() {
        return fillColor.getValue();
    }

    public TextField getHeightField() {
        return heightField;
    }

    public TextField getRotateField() {
        return rotateField;
    }

    public TextField getWidthField() {
        return widthField;
    }

    public TextField getXPointField() {
        return xPointField;
    }

    public TextField getYPointField() {
        return yPointField;
    }
}
