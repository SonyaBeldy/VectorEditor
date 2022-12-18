package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Polyline extends Figure{

    ArrayList<Point> points;

    Color fillColor;

    public Polyline(Color fillColor) {
        this.fillColor = fillColor;
        points = new ArrayList<>();
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(Paint.valueOf(String.valueOf(fillColor)));
        for (int i = 0; i < points.size() - 1; i++) {
            Line line = new Line(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY(), fillColor);
            line.draw(graphicsContext);
        }

    }


    public void addPoint(Point point) {
        points.add(point);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
}
