package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Line extends Figure {

    private Color figureColor;
    public Line(double startX, double startY, double endX, double endY, Color figureColor) {
        startPoint = new Point(startX, startY);
        endPoint = new Point(endX, endY);
        this.figureColor = figureColor;
    }

    public void draw(GraphicsContext graphicsContext) {
        System.out.println("draw");
        //graphicsContext.setStroke(Paint.valueOf("#ff3de8"));
        graphicsContext.setStroke(Paint.valueOf(String.valueOf(figureColor)));
        //graphicsContext.setStroke(Color.rgb(figureColor.getRed(), figureColor.getGreen(), figureColor.getBlue()));

        graphicsContext.getCanvas().getGraphicsContext2D().strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(),  endPoint.getY());
    }

    public void draw(GraphicsContext graphicsContext, Point start, Point end) {
        System.out.println("draw");
        graphicsContext.setStroke(Paint.valueOf(String.valueOf(figureColor)));
        graphicsContext.getCanvas().getGraphicsContext2D().strokeLine(start.getX(), start.getY(), end.getX(),  end.getY());
    }



    public void drawHitbox(GraphicsContext graphicsContext) {

        double indent = 10;

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

    public void drawHitbox(GraphicsContext graphicsContext, Point start, Point end) {
        double indent = 10;

        double minX = Math.min(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double maxX = Math.max(start.getX(), end.getX());
        double maxY = Math.max(start.getY(), end.getY());

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
