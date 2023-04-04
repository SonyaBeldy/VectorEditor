package com.example.vectoreditor.model.drawers;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.figures.FigureDecorationData;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class PolygonDrawer extends Drawer {

    private final ArrayList<Point> points;

    public PolygonDrawer(ArrayList<Point> points) {
        this.points = points;
    }
    @Override
    public void draw(GraphicsContext graphicsContext, FigureTransformData figureTransformData, FigureDecorationData figureDecorationData) {
        super.draw(graphicsContext, figureTransformData, figureDecorationData);
        for (int i = 0; i < points.size() - 1; i++) {
            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
        }
        graphicsContext.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY());
    }


}
