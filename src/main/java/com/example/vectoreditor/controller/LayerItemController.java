package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Layer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class LayerItemController implements Initializable {


    Layer layer;

    @FXML
    private Rectangle color;

    @FXML
    private TextField layerName;

    @FXML
    private Button showFigures;

    @FXML
    private ImageView visible;

    public LayerItemController() {

    }

    @FXML
    void layerNameAction(ActionEvent event) {
//        layerName.setEditable(false);
//        layerName.setOnMouseClicked(event -> {
//            if(event.getClickCount() == 2) {
//                layerName.setEditable(true);
//
//            }
//        });
    }

    @FXML
    void showFiguresButton(ActionEvent event) {

    }

    public void setLayer(Layer layer) {
        this.layer = layer;
        layerName.setText(layer.getName());
    }

    public void setLayerName(String name) {
        layerName.setText(name);
    }

    public void changeColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        color.setFill(Color.color(r, g, b));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeColor();
    }
}
