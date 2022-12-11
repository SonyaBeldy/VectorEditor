package com.example.vectoreditor;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Figure {

    Line (double startX, double startY, double endX, double endY) {
        startPoint = new Point(startX, startY);
        endPoint = new Point(endX, endY);
    }

    public void draw(GraphicsContext graphicsContext) {
        System.out.println("draw");
        graphicsContext.getCanvas().getGraphicsContext2D().strokeLine(startPoint.x, startPoint.y, endPoint.x,  endPoint.y);
    }

    public void draw(GraphicsContext graphicsContext, Point start, Point end) {
        System.out.println("draw");
        graphicsContext.getCanvas().getGraphicsContext2D().strokeLine(start.x, start.y, end.x,  end.y);
    }



    public void highlightCurrant(GraphicsContext graphicsContext) {
        double indent = 10;

        double minX = getMinX();
        double minY = getMinY();
        double maxX = getMaxX();
        double maxY = getMaxY();

        graphicsContext.strokeLine(minX - indent, minY - indent, maxX + indent, minY - indent);
        graphicsContext.strokeLine(maxX + indent, minY - indent, maxX + indent, maxY + indent);
        graphicsContext.strokeLine(maxX + indent, maxY + indent, minX - indent, maxY + indent);
        graphicsContext.strokeLine(minX - indent, maxY + indent, minX - indent, minY - indent);

    }

    public void highlightCurrant(GraphicsContext graphicsContext, Point start, Point end) {
        double indent = 10;

        double minX = Math.min(start.x, end.x);
        double minY = Math.min(start.y, end.y);
        double maxX = Math.max(start.x, end.x);
        double maxY = Math.max(start.y, end.y);

        graphicsContext.strokeLine(minX - indent, minY - indent, maxX + indent, minY - indent);
        graphicsContext.strokeLine(maxX + indent, minY - indent, maxX + indent, maxY + indent);
        graphicsContext.strokeLine(maxX + indent, maxY + indent, minX - indent, maxY + indent);
        graphicsContext.strokeLine(minX - indent, maxY + indent, minX - indent, minY - indent);

    }
    public boolean inHitBox(double x, double y) {
        return (x > getMinX()) && (x < getMaxX()) && (y > getMinY()) && (y < getMaxY());
    }
    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(double x, double y) {
        startPoint.x = x;
        startPoint.y = y;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(double x, double y) {
        endPoint.x = x;
        endPoint.y = y;
    }

    public double getMinX() {
        return Math.min(startPoint.x, endPoint.x);
    }

    public  double getMinY() {
        return Math.min(startPoint.y, endPoint.y);
    }

    public double getMaxX() {
        return Math.max(startPoint.x, endPoint.x);
    }

    public  double getMaxY() {
        return Math.max(startPoint.y, endPoint.y);
    }




}
