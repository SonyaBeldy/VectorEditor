package com.example.vectoreditor.controller;

import javafx.fxml.FXMLLoader;
import org.w3c.dom.Node;

import java.io.IOException;

public class MyFXMLLoader {
    public static NodeController<FigureItemController> loadFigureItem() {
        return loadFxml("/com/example/vectoreditor/figure_item.fxml");
    }

    public static NodeController<LayerItemController> loadLayerItem() {
        return loadFxml("/com/example/vectoreditor/layer_item.fxml");
    }

    public static NodeController<CanvasViewController> loadCanvasView() {
        return loadFxml("/com/example/vectoreditor/canvas-view.fxml");
    }

    public static <T> NodeController<T> loadFxml(String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MyFXMLLoader.class.getResource(fxmlPath));
        try {
            return new NodeController<>(fxmlLoader.load(), fxmlLoader.getController());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
