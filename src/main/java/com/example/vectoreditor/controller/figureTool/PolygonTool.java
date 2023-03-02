package com.example.vectoreditor.controller.figureTool;

import com.example.vectoreditor.controller.CanvasController;
import com.example.vectoreditor.controller.ITool;
import com.example.vectoreditor.model.figure.Polygon;
import com.example.vectoreditor.model.figure.Polyline;
import javafx.scene.paint.Color;

public class PolygonTool extends PolylineTool implements ITool {

    public PolygonTool(CanvasController canvasController) {
        super(canvasController);
        figure = new Polygon(Color.BLACK);
    }
    @Override
    public void createFigure() {
        figure = new Polygon(canvasController.getStrokeColor());
    }

}
