package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Figure implements Cloneable<Figure>{

    ArrayList<Point> points;
    IDrawer drawer;
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

    public ArrayList<Point> getPoints() { return points; }

    public Color getStrokeColor() {
        return null;
    }

    public void setEndPoint(double x, double y) {

    }

    @Override
    public Figure clone() {
        return null;
    }
}
