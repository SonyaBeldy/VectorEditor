package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.figure.Figure;
import com.example.vectoreditor.model.figure.FigureProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PropertiesBoxController implements Initializable {

    private FigureProperties properties;
    private CanvasViewController canvasViewController;
    @FXML
    private ColorPicker strokeColor;

    @FXML
    private ColorPicker fillColor;

    @FXML
    private TextField heightField;

    @FXML
    private ScrollPane propScrollPane;

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
    }
    @FXML
    void chooseFillColor(ActionEvent event) {

    }

    @FXML
    void chooseStrokeColor(ActionEvent event) {
        canvasViewController.setStrokeColor(strokeColor.getValue());
    }

    @FXML
    void move(ActionEvent event) {
        if(canvasViewController.getCurrentFigureController().isEmpty()) {
            return;
        }
        Figure figure = canvasViewController.getCurrentFigureController().get().getFigure();

        Point newCenter = new Point(Double.parseDouble(xPointField.getText()), Double.parseDouble(yPointField.getText()));
        Point center = figure.getCenter();
        Point shift = new Point(newCenter.getX() - center.getX(), newCenter.getY() - center.getY());
        figure.move(shift);
        canvasViewController.redrawAllFigures();
    }

    @FXML
    void rotate(ActionEvent event) {
        if(canvasViewController.getCurrentFigureController().isEmpty()) {
            return;
        }
        double angle = Double.parseDouble(rotateField.getText());
        angle = Math.toRadians(angle);
        Figure figure = canvasViewController.getCurrentFigureController().get().getFigure();

        double angleDiff = angle - figure.getAngle();
        figure.rotate(figure, figure.getCenter(), angleDiff);
        figure.setAngle(angle);
        canvasViewController.redrawAllFigures();
    }

    public void update() {

    }

    public void initProperties(FigureProperties properties) {
        properties.setFill(Optional.of(fillColor.getValue()));
        properties.setStrokeColor(Optional.of(strokeColor.getValue()));
//        properties.setStrokeWidth();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillColor.setValue(Color.BLACK);
        strokeColor.setValue(Color.BLACK);

        widthField.setText("hello");
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

    public ColorPicker getStrokeColor() {
        return strokeColor;
    }

    public ColorPicker getFillColor() {
        return fillColor;
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
