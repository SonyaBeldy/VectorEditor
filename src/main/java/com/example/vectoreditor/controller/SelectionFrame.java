package com.example.vectoreditor.controller;

import com.example.vectoreditor.controller.figureTool.RectangleTool;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.drawer.RectangleDrawer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SelectionFrame {
    GraphicsContext graphicsContext;
    ArrayList<Point> points = new ArrayList<>();
    public SelectionFrame(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;

    }

    public void draw(MainController mainController, Point pressPoint, Point dragPoint) {
        Canvas drawCanvas = mainController.getCurrentCanvasController().getDrawCanvas();
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());

        mainController.getCurrentCanvasController().redrawAllFigures();

        RectangleDrawer drawer = new RectangleDrawer(points);
        drawer.draw(graphicsContext, Color.LIGHTGRAY);
    }

    public void addPoint(Point point) {
        points.add(point);
    }
}
