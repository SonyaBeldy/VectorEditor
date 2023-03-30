package com.example.vectoreditor.model.drawer;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.figure.Figure;
import com.example.vectoreditor.model.figure.FigureDecorationData;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class PolylineDrawer extends Drawer {

    private final ArrayList<Point> points;
    private Figure figure;

    public PolylineDrawer(ArrayList<Point> points) {
        this.points = points;
    }

    @Override
    public void draw(GraphicsContext graphicsContext, FigureTransformData figureTransformData, FigureDecorationData figureDecorationData) {
        if (figureDecorationData.getStrokeColor().isPresent()) {
            graphicsContext.setStroke(figureDecorationData.getStrokeColor().get());
        }
        graphicsContext.setLineDashes(figureDecorationData.getLineDashes());
        for (int i = 0; i < points.size() - 1; i++) {
            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
        }

    }

}
