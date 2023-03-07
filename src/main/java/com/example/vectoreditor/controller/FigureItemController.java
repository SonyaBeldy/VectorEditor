package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.figure.Figure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class FigureItemController {

    Figure figure;

    @FXML
    private Rectangle color;

    @FXML
    private TextField figureName;

    @FXML
    private Button showFigures;

    @FXML
    void visibleButtonClick(ActionEvent event) {

    }
    @FXML
    void figureNameAction(ActionEvent event) {

    }

    @FXML
    void showFiguresButton(ActionEvent event) {

    }

    public void setFigure(Figure figure) {
        this.figure = figure;
        figureName.setText("<" + figure.getName() + ">");
    }
}
