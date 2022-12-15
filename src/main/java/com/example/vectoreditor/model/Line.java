package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Line extends Figure {

    private Color fillColor;

    Point[] hitBoxEdgePoints;

    public Line(double startX, double startY, double endX, double endY, Color fillColor) {
        startPoint = new Point(startX, startY);
        endPoint = new Point(endX, endY);
        this.fillColor = fillColor;
        hitBoxEdgePoints = new Point[4];
    }

    public void calculateHitBoxPoints() {
        double indent = 1;

        hitBoxEdgePoints[0] = new Point(getMinX() - indent, getMinY() - indent);
        hitBoxEdgePoints[1] = new Point(getMaxX() + indent, getMinY() - indent);
        hitBoxEdgePoints[2] = new Point(getMaxX() + indent, getMaxY() + indent);
        hitBoxEdgePoints[3] = new Point(getMinX() - indent, getMaxY() + indent);
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(Paint.valueOf(String.valueOf(fillColor)));
        graphicsContext.getCanvas().getGraphicsContext2D().strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(),  endPoint.getY());
    }

    public void drawHitbox(GraphicsContext graphicsContext) {

        calculateHitBoxPoints();
        graphicsContext.setStroke(Paint.valueOf("#ff3de8"));

        for (int i = 0; i < hitBoxEdgePoints.length - 1; i++) {
            graphicsContext.strokeLine(hitBoxEdgePoints[i].getX(), hitBoxEdgePoints[i].getY(), hitBoxEdgePoints[i + 1].getX(), hitBoxEdgePoints[i + 1].getY());
        }
        graphicsContext.strokeLine(hitBoxEdgePoints[0].getX(), hitBoxEdgePoints[0].getY(), hitBoxEdgePoints[3].getX(), hitBoxEdgePoints[3].getY());

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
