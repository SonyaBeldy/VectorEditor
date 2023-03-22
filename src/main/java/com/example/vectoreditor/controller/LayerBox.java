package com.example.vectoreditor.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class LayerBox extends VBox {

    private final ArrayList<LayerItemController> layers = new ArrayList<>();
    private LayerItemController currentLayer;
    private MainController mainController;

    public LayerBox(MainController mainController) {
        this.mainController = mainController;
    }

    protected void createLayer() {
        NodeController<LayerItemController> layerItem = MyFXMLLoader.loadLayerItem();

        Color color;
        if (layers.size() < 1) {
            color = Color.hsb(185, 0.8, 1);
            color = Color.MEDIUMSLATEBLUE;
        } else {
            color = layers.get(layers.size() - 1).getColor();
            color = nextLayerColor(color);
        }
        layerItem.controller.init(this, generateDefaultLayerName(), color);

        getChildren().add(0, layerItem.node);
        layers.add(layerItem.controller);
        layerItem.controller.highlight();
        currentLayer = layerItem.controller;

    }

    public Color nextLayerColor(Color oldColor) {
        double oldHue = oldColor.getHue();
        return Color.hsb(oldHue + 70, 0.8, 1);
    }

    private String generateDefaultLayerName() {
        int number = layers.size() + 1;
        String name = "Layer " + number;
        int i = 0;
        while (i != layers.size()) {
            if(layers.get(i).getName().equals(name)) {
                number++;
                name = "Layer " + number;
                i = 0;
            } else {
                i++;
            }
        }
        return name;
    }

    protected boolean canDeleteLayer() {
        return layers.size() > 1;
    }
    protected void deleteCurrentLayer() {
        if (layers.size() < 2) {
            throw new RuntimeException("layers.size() < 2");
        }
        int currentLayerInd = layers.indexOf(currentLayer);
        if (currentLayerInd > -1) {
            getChildren().remove(currentLayerInd);
            layers.remove(currentLayerInd);
            if (currentLayerInd == layers.size()) {
                currentLayerInd = 0;
            }
            currentLayer = layers.get(currentLayerInd);
            currentLayer.highlight();
        }
    }

    public LayerItemController getCurrentLayer() {
        return currentLayer;
    }

    public void setCurrentLayer(LayerItemController currentLayer) {
        this.currentLayer = currentLayer;
    }

    public MainController getMainController() {
        return mainController;
    }

    public ArrayList<LayerItemController> getLayers() {
        return layers;
    }
}
