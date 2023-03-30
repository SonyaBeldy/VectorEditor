package com.example.vectoreditor.model.drawer;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.figure.FigureDecorationData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Drawer {
    public void draw(GraphicsContext graphicsContext, FigureTransformData figureTransformData, FigureDecorationData figureDecorationData) {
        if (figureDecorationData.getStrokeColor().isPresent()) {
            graphicsContext.setStroke(figureDecorationData.getStrokeColor().get());
        }
        graphicsContext.setLineDashes(figureDecorationData.getLineDashes());
    }

}
