package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Figure {

    Point startPoint;
    Point endPoint;

    public Figure() {

    }


    public void draw(GraphicsContext graphicsContext) {

    }

    public void calculateHitBoxPoints() {

    }

    public void drawHitbox(GraphicsContext graphicsContext) {

    }

    public void highlight(GraphicsContext graphicsContext){

    }

    public boolean isClickedOn(double x, double y) {
        return false;
    }


    public void setStrokeColor(Color figureColor) {
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
