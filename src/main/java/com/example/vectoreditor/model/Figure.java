package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Figure {

    ArrayList<Point> points;

    Point startPoint;
    Point endPoint;

    Figure () {

    }


    public void draw(GraphicsContext graphicsContext) {

    }

    public void draw(GraphicsContext graphicsContext, Point start, Point end) {

    }

    public void drawHitbox(GraphicsContext graphicsContext) {

    }

    public void drawHitbox(GraphicsContext graphicsContext, Point start, Point end) {

    }

    public boolean isClickedOn(double x, double y) {
        return false;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setStartPoint(double x, double y) {

    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
    public void setEndPoint(double x, double y) {

    }

    public static class Select {
    }
}
