package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Line extends Figure {

    private Color strokeColor;

    Point[] hitBoxEdgePoints;

    public Line(Color strokeColor) {
        super(strokeColor);
        //startPoint = new Point(startX, startY);
        //endPoint = new Point(endX, endY);
        this.strokeColor = strokeColor;
        hitBoxEdgePoints = new Point[4];
    }

    public void calculateHitBoxPoints() {
        double indent = 1;
//
//        hitBoxEdgePoints[0] = new Point(getMinX() - indent, getMinY() - indent);
//        hitBoxEdgePoints[1] = new Point(getMaxX() + indent, getMinY() - indent);
//        hitBoxEdgePoints[2] = new Point(getMaxX() + indent, getMaxY() + indent);
//        hitBoxEdgePoints[3] = new Point(getMinX() - indent, getMaxY() + indent);
    }

    public void draw(GraphicsContext graphicsContext) {
        draw(graphicsContext, strokeColor);
    }

    public void draw(GraphicsContext graphicsContext, Color color) {
        graphicsContext.setStroke(color);
        //graphicsContext.getCanvas().getGraphicsContext2D().strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(),  endPoint.getY());
    }

    public void drawHitbox(GraphicsContext graphicsContext) {
        calculateHitBoxPoints();
        graphicsContext.setStroke(Paint.valueOf("#ff3de8"));

        for (int i = 0; i < hitBoxEdgePoints.length - 1; i++) {
            graphicsContext.strokeLine(hitBoxEdgePoints[i].getX(), hitBoxEdgePoints[i].getY(), hitBoxEdgePoints[i + 1].getX(), hitBoxEdgePoints[i + 1].getY());
        }
        graphicsContext.strokeLine(hitBoxEdgePoints[0].getX(), hitBoxEdgePoints[0].getY(), hitBoxEdgePoints[3].getX(), hitBoxEdgePoints[3].getY());

    }

    public void highlight(GraphicsContext graphicsContext) {
        draw(graphicsContext, Color.LIGHTBLUE);
    }

//    public boolean isClickedOn(double x, double y) {
//        return (x > getMinX()) && (x < getMaxX()) && (y > getMinY()) && (y < getMaxY());
//    }

//    public double getMinX() {
//        return Math.min(startPoint.getX(), endPoint.getX());
//    }
//
//    public  double getMinY() {
//        return Math.min(startPoint.getY(), endPoint.getY());
//    }
//
//    public double getMaxX() {
//        return Math.max(startPoint.getX(), endPoint.getX());
//    }
//
//    public  double getMaxY() {
//        return Math.max(startPoint.getY(), endPoint.getY());
//    }

    public void setStrokeColor(Color figureColor) {
        this.strokeColor = figureColor;
    }

    @Override
    public Figure clone() {
        return null;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

//    public Point getStartPoint() {
//        return startPoint;
//    }
//
//    public void setStartPoint(double x, double y) {
//        startPoint.setX(x);
//        startPoint.setY(y);
//    }
//
//    public Point getEndPoint() {
//        return endPoint;
//    }
//
//    public void setEndPoint(double x, double y) {
//        endPoint.setX(x);
//        endPoint.setY(y);
//    }


}
