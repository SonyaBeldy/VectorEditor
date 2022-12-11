package com.example.vectoreditor;

import javafx.scene.canvas.GraphicsContext;

public class Figure {

    Point startPoint;
    Point endPoint;

    Figure () {

    }


    public void draw(GraphicsContext graphicsContext) {
    }

    public void draw(GraphicsContext graphicsContext, Point start, Point end) {

    }

    public void highlightCurrant(GraphicsContext graphicsContext) {

    }

    public void highlightCurrant(GraphicsContext graphicsContext, Point start, Point end) {

    }

    public boolean inHitBox(double x, double y) {
        return false;
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
}
