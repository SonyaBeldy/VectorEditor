package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Figure {

    Point startPoint;
    Point endPoint;

    Drawer drawer;
    BordersPainter bordersPainter;

    public Figure() {

    }

    public void draw(GraphicsContext graphicsContext) {

    }

    public void drawBorders(GraphicsContext graphicsContext) {

    }

    public void highlight(GraphicsContext graphicsContext) {

    }
    public void calcBoardsPoints() {
    }
    public ArrayList<Point> getBoardsPoints() {
        return null;
    }

    public boolean isClickedOn(double x, double y) {
        return false;
    }


    public void setStrokeColor(Color figureColor) {
    }

    public Color getStrokeColor() {
        return null;
    }
    public Point getStartPoint() {
        return startPoint;
    }
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
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
