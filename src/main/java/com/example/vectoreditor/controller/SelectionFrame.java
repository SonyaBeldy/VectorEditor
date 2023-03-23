package com.example.vectoreditor.controller;

import com.example.vectoreditor.controller.figureTool.RectangleTool;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.drawer.RectangleDrawer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SelectionFrame {
    GraphicsContext graphicsContext;

    public SelectionFrame(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void draw(Point pressPoint, Point dragPoint) {
//        ArrayList<Point> points = new ArrayList<>();
//        points.add(pressPoint);
//        points.add(dragPoint);
//        RectangleDrawer rectangleDrawer = new RectangleDrawer(points);
//        rectangleDrawer.draw(graphicsContext, Color.LIGHTGRAY);
    }
}
