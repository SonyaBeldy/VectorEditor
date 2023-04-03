package com.example.vectoreditor.controller.figureTool;

import com.example.vectoreditor.controller.ITool;
import com.example.vectoreditor.controller.MainController;
import com.example.vectoreditor.model.figure.Polygon;
import javafx.scene.paint.Color;

public class PolygonTool extends PolylineTool implements ITool {

    public PolygonTool(MainController mainController) {
        super(mainController);
        figure = new Polygon(mainController.getPropertiesBoxController().getDecorationProperties());
    }

}
