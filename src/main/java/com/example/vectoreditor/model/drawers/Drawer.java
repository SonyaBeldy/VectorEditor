package com.example.vectoreditor.model.drawers;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.figures.FigureDecorationData;
import javafx.scene.canvas.GraphicsContext;

public abstract class Drawer {
    public void draw(GraphicsContext graphicsContext, FigureTransformData figureTransformData, FigureDecorationData figureDecorationData) {
        if (figureDecorationData.getStrokeColor().isPresent()) {
            graphicsContext.setStroke(figureDecorationData.getStrokeColor().get());
        }
        graphicsContext.setLineDashes(figureDecorationData.getLineDashes());
    }

}
