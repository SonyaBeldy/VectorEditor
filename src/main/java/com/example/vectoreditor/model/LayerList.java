package com.example.vectoreditor.model;

import java.util.ArrayList;
import java.util.Optional;

public class LayerList {

    private final ArrayList<Layer> layers = new ArrayList<>();

    public LayerList(int layersCount) {
        for (int i = 0; i < layersCount; i++) {
            createNewLayer();
        }
    }

    public void createNewLayer() {
        layers.add(new Layer(generateDefaultLayerName()));
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

    public Layer get(int ind) {
        return layers.get(ind);
    }

    public Optional<Layer> getLast() {
        if (layers.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(layers.get(layers.size() - 1));
    }

    public int getSize() {
        return layers.size();
    }
}
