package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Line extends Figure {

    private Color fillColor;
    public Line(double startX, double startY, double endX, double endY, Color fillColor) {
        startPoint = new Point(startX, startY);
        endPoint = new Point(endX, endY);
        this.fillColor = fillColor;
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(Paint.valueOf(String.valueOf(fillColor)));
        graphicsContext.getCanvas().getGraphicsContext2D().strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(),  endPoint.getY());
    }

    public void drawHitbox(GraphicsContext graphicsContext) {

        double indent = 1;

        double minX = getMinX();
        double minY = getMinY();
        double maxX = getMaxX();
        double maxY = getMaxY();

        graphicsContext.setStroke(Paint.valueOf("#ff3de8"));

        graphicsContext.strokeLine(minX - indent, minY - indent, maxX + indent, minY - indent);
        graphicsContext.strokeLine(maxX + indent, minY - indent, maxX + indent, maxY + indent);
        graphicsContext.strokeLine(maxX + indent, maxY + indent, minX - indent, maxY + indent);
        graphicsContext.strokeLine(minX - indent, maxY + indent, minX - indent, minY - indent);

    }

    public boolean isClickedOn(double x, double y) {
        return (x > getMinX()) && (x < getMaxX()) && (y > getMinY()) && (y < getMaxY());
    }

    public double getMinX() {
        return Math.min(startPoint.getX(), endPoint.getX());
    }

    public  double getMinY() {
        return Math.min(startPoint.getY(), endPoint.getY());
    }

    public double getMaxX() {
        return Math.max(startPoint.getX(), endPoint.getX());
    }

    public  double getMaxY() {
        return Math.max(startPoint.getY(), endPoint.getY());
    }

    public void setFigureColor(Color figureColor) {
        this.fillColor = figureColor;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(double x, double y) {
        startPoint.setX(x);
        startPoint.setY(y);
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(double x, double y) {
        endPoint.setX(x);
        endPoint.setY(y);
    }


}
