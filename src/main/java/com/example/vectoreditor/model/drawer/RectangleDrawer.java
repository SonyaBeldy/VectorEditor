package com.example.vectoreditor.model.drawer;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.figure.FigureDecorationData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class RectangleDrawer extends Drawer {

    private final ArrayList<Point> points;

    public RectangleDrawer(ArrayList<Point> points) {
        this.points = points;
    }

    @Override
    public void draw(GraphicsContext graphicsContext, FigureTransformData figureTransformData, FigureDecorationData figureDecorationData) {
        super.draw(graphicsContext, figureTransformData, figureDecorationData);
        for (int i = 0; i < points.size() - 1; i++) {
            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
        }
        graphicsContext.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(3).getX(), points.get(3).getY());
    }

}
