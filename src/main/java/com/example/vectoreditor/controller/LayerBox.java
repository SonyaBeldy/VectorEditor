package com.example.vectoreditor.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class LayerBox extends VBox {

    private final ArrayList<LayerItemController> layers = new ArrayList<>();
    private Optional<LayerItemController> currentLayer;

    protected void createLayer() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/vectoreditor/layer_item.fxml"));
        VBox layerItem;
        try {
            layerItem = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LayerItemController layerItemController = fxmlLoader.getController();

        Color color;
        if (layers.size() < 1) {
            color = Color.hsb(185, 0.8, 1);
        } else {
            color = layers.get(layers.size() - 1).getColor();
        }
        layerItemController.init(this, generateDefaultLayerName(), nextLayerColor(color));

        getChildren().add(layerItem);
        layers.add(layerItemController);
        layerItemController.highlight();
        currentLayer = Optional.of(layerItemController);
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
            if(layers.get(i).getLayer().getName().equals(name)) {
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
        if (currentLayer.isPresent()) {
            int currentLayerInd = layers.indexOf(currentLayer.get());
            if (currentLayerInd > -1) {
                getChildren().remove(currentLayerInd);
                layers.remove(currentLayerInd);
                if (currentLayerInd == layers.size()) {
                    currentLayerInd = 0;
                }
                currentLayer = Optional.of(layers.get(currentLayerInd));
                currentLayer.orElseThrow().highlight();
            }
        }
    }

    public Optional<LayerItemController> getCurrentLayer() {
        return currentLayer;
    }

    public void setCurrentLayer(Optional<LayerItemController> currentLayer) {
        this.currentLayer = currentLayer;
    }

    public ArrayList<LayerItemController> getLayers() {
        return layers;
    }
}
