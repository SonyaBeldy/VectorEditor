package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Polyline extends Figure{

    private final ArrayList<Point> points;
    private final ArrayList<Point> boardsPoints;
    private final ArrayList<Point> hitboxPoints;
    private final Color strokeColor;

    public Polyline(Color strokeColor) {
        this.strokeColor = strokeColor;
        points = new ArrayList<>();
        drawer = new PolylineDrawer(points);
        bordersPainter = new BordersPainter(this);
        boardsPoints = new ArrayList<>();
        hitboxPoints = new ArrayList<>();
    }

    public void draw(GraphicsContext graphicsContext) {
        drawer.draw(graphicsContext, strokeColor);
    }

    public void highlight(GraphicsContext graphicsContext) {
        drawer.draw(graphicsContext, Color.LIGHTBLUE);
    }

    public void drawBorders(GraphicsContext graphicsContext) {
        bordersPainter.drawBoards(graphicsContext);
    }
    public void calcBoardsPoints() {
        System.out.println("calc");
        boardsPoints.clear();
        double minX = points.get(0).getX();
        double minY = points.get(0).getY();
        double maxX = points.get(1).getX();
        double maxY = points.get(1).getY();
        for (Point point : points) {
            if (point.getX() <= minX) {
                minX = point.getX();
            }
            if (point.getY() <= minY) {
                minY = point.getY();
            }
            if (point.getX() > maxX) {
                maxX = point.getX();
            }
            if (point.getY() > maxY) {
                maxY = point.getY();
            }
        }
        System.out.println("min x " + minX + ", min y " + minY);
        System.out.println("max x " + maxX + ", max y " + maxY);
        boardsPoints.add(new Point(minX, minY));
        boardsPoints.add(new Point(maxX, minY));
        boardsPoints.add(new Point(maxX, maxY));
        boardsPoints.add(new Point(minX, maxY));
    }

    public ArrayList<Point> getBoardsPoints() {
        return boardsPoints;
    }

    public void calcHitboxPoints() {
    }
    public boolean isClickedOn(double x, double y) {
        //double hitDistance = 10;
        return  (x < getBoardsPoints().get(2).getX()) && (x > getBoardsPoints().get(0).getX()) && (y < getBoardsPoints().get(2).getY()) && (y > getBoardsPoints().get(0).getY());
            //return (x > getBoardsPoints().get(0).getX()) && (x < getBoardsPoints().get(2).getX()) && (y > getBoardsPoints().get(0).getY()) && (y < getBoardsPoints().get(2).getY());
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }
    public void setStrokeColor(Color figureColor) {
    }
}
