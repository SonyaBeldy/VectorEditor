package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.figure.Figure;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FigureItemController implements Initializable {

    private LayerItemController layerItemController;
    private Figure figure;
    private MainController mainController;
    @FXML
    private TextField figureName;

    @FXML
    private HBox figureItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        figureName.setEditable(false);
        figureName.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                figureName.setEditable(true);
                figureName.setCursor(Cursor.TEXT);
            } else {
                figureItem.fireEvent(event);
            }
        });
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    void figureItemClick() {
        mainController.getCurrentCanvasController().setCurrentFigureController(Optional.of(this));
        highlight();
    }
    @FXML
    void visibleButtonClick(ActionEvent event) {

    }

    @FXML
    void figureNameAction(ActionEvent event) {

    }

    public void init(MainController mainController, LayerItemController layerItemController) {
        this.layerItemController = layerItemController;
        this.mainController = mainController;
    }

    public void highlight() {
        ObservableList<Node> childrenUnmodifiable = figureItem.getParent().getParent().getChildrenUnmodifiable();
        for (Node node : childrenUnmodifiable) {
            node.setStyle("-fx-background-color: transparent");
        }
        figureItem.getParent().setStyle("-fx-background-color: dodgerblue");
    }

    public LayerItemController getLayerController() {
        return layerItemController;
    }

    public void setLayerController(LayerItemController layerItemController) {
        this.layerItemController = layerItemController;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
        figureName.setText("<" + figure.getName() + ">");
    }

}
