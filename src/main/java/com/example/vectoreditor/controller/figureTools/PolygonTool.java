package com.example.vectoreditor.controller.figureTools;

import com.example.vectoreditor.controller.ITool;
import com.example.vectoreditor.controller.MainController;
import com.example.vectoreditor.model.figures.Polygon;

public class PolygonTool extends PolylineTool implements ITool {

    public PolygonTool(MainController mainController) {
        super(mainController);
    }

    @Override
    protected void createFigure() {
        figure = new Polygon(mainController.getPropertiesBoxController().getDecorationProperties());
    }

}
