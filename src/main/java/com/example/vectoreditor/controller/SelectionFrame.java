package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.figures.FigureDecorationData;
import com.example.vectoreditor.model.figures.Rectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Optional;

public class SelectionFrame {
    GraphicsContext graphicsContext;
    ArrayList<Point> points = new ArrayList<>();
    public SelectionFrame(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;

    }

    public void draw(MainController mainController, Point pressPoint, Point dragPoint) {
        Canvas drawCanvas = mainController.getCurrentCanvasController().getDrawCanvas();
        //drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());

        mainController.getCurrentCanvasController().redrawAllFigures();

        FigureDecorationData figureDecorationData = new FigureDecorationData(Optional.of(Color.GRAY), Optional.empty(), 1);
        Rectangle rectangle = new Rectangle(figureDecorationData);

        rectangle.draw(graphicsContext);
    }

    public void addPoint(Point point) {
        points.add(point);
    }
}
