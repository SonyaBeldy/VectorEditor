package com.example.vectoreditor.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Polyline extends Line{

    ArrayList<Point> points;

    Color fillColor;

    public Polyline(double startX, double startY, double endX, double endY, Color fillColor) {
        super(startX, startY, endX, endY, fillColor);
        points = new ArrayList<>();
        points.add(startPoint);
        points.add(endPoint);
    }

    public void addPoint(double x, double y) {
        points.add(new Point(x, y));
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
}
