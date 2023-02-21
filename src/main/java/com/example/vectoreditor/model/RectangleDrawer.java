package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class RectangleDrawer implements IDrawer{

    private final ArrayList<Point> points;

    public RectangleDrawer(ArrayList<Point> points) {
        this.points = points;
    }

    @Override
    public void draw(GraphicsContext graphicsContext, Color color) {
        for (int i = 0; i < points.size() - 1; i++) {
            graphicsContext.setStroke(color);
            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
        }
        graphicsContext.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(3).getX(), points.get(3).getY());

    }
}
