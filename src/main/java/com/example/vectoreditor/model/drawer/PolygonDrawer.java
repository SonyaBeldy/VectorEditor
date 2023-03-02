package com.example.vectoreditor.model.drawer;

import com.example.vectoreditor.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PolygonDrawer implements IDrawer{

    private final ArrayList<Point> points;

    public PolygonDrawer(ArrayList<Point> points) {
        this.points = points;
    }
    @Override
    public void draw(GraphicsContext graphicsContext, Color color) {
        for (int i = 0; i < points.size() - 1; i++) {
            graphicsContext.setStroke(color);
            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
        }
        graphicsContext.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY());
    }
}
