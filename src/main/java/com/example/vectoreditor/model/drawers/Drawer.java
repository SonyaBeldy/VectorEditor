package com.example.vectoreditor.model.drawers;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.figures.FigureDecorationData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Optional;

public abstract class Drawer {

    GraphicsContext graphicsContext;

    public void draw(GraphicsContext graphicsContext, FigureTransformData figureTransformData, FigureDecorationData figureDecorationData) {
        this.graphicsContext = graphicsContext;

        setLineColor(figureDecorationData.getLineColor());
        setFillColor(figureDecorationData.getFillColor());
        setLineWidth(figureDecorationData.getStrokeWidth());
        setLineDashes(figureDecorationData.getLineDashes());

    }

    private void setLineColor(Optional<Color> color) {
        if (color.isEmpty()) {
            return;
        }
        graphicsContext.setStroke(color.get());
    }

    private void setFillColor(Optional<Color> color) {
        if(color.isEmpty()) {
            return;
        }
        graphicsContext.setFill(color.get());
    }

    private void setLineWidth(double lineWidth) {
        graphicsContext.setLineWidth(lineWidth);
    }

    private void setLineDashes(double lineDashes) {
        graphicsContext.setLineDashes(lineDashes);
    }

}
