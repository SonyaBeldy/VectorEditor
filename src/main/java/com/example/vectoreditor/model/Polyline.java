package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Polyline extends Figure implements Cloneable<Figure> {

    private final ArrayList<Point> points;
    private final ArrayList<Point> boardsPoints;
    private final ArrayList<Point> rotatePoints;
    private final ArrayList<Point> resizePoints;
    private final ArrayList<Point> hitboxPoints;
    private final Color strokeColor;

    public Polyline(Color strokeColor) {
        this.strokeColor = strokeColor;
        points = new ArrayList<>();
        boardsPoints = new ArrayList<>();
        hitboxPoints = new ArrayList<>();
        rotatePoints = new ArrayList<>();
        resizePoints = new ArrayList<>();
        drawer = new PolylineDrawer(points);
    }

    public void draw(GraphicsContext graphicsContext) {
        drawer.draw(graphicsContext, strokeColor);
    }

    public void highlight(GraphicsContext graphicsContext) {
        drawer.draw(graphicsContext, Color.MEDIUMSLATEBLUE);
    }

    public void calcBoardsPoints() {
        boardsPoints.clear();
        double minX = points.get(0).getX();
        double minY = points.get(0).getY();
        double maxX = points.get(0).getX();
        double maxY = points.get(0).getY();
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
        boardsPoints.add(new Point(minX, minY));
        boardsPoints.add(new Point(maxX, minY));
        boardsPoints.add(new Point(maxX, maxY));
        boardsPoints.add(new Point(minX, maxY));

        calcRotatePoints();
        calcResizePoints();
    }
    public void calcRotatePoints() {

        rotatePoints.clear();
        double hitbox = 10;

        rotatePoints.add(new Point(boardsPoints.get(0).getX() - hitbox, boardsPoints.get(0).getY() - hitbox));
        rotatePoints.add(new Point(boardsPoints.get(1).getX() + hitbox, boardsPoints.get(1).getY() - hitbox));
        rotatePoints.add(new Point(boardsPoints.get(2).getX() + hitbox, boardsPoints.get(2).getY() + hitbox));
        rotatePoints.add(new Point(boardsPoints.get(3).getX() - hitbox, boardsPoints.get(3).getY() + hitbox));
    }
    public void calcResizePoints() {
        resizePoints.clear();

        resizePoints.add(new Point((boardsPoints.get(1).getX() - boardsPoints.get(0).getX())/2 + boardsPoints.get(0).getX(), boardsPoints.get(0).getY()));
        resizePoints.add(new Point(boardsPoints.get(1).getX(), (boardsPoints.get(1).getY() - boardsPoints.get(2).getY())/2 + boardsPoints.get(2).getY()));
        resizePoints.add(new Point((boardsPoints.get(2).getX() - boardsPoints.get(3).getX())/2 + boardsPoints.get(3).getX(), boardsPoints.get(2).getY()));
        resizePoints.add(new Point(boardsPoints.get(3).getX(), (boardsPoints.get(0).getY() - boardsPoints.get(3).getY())/2 + boardsPoints.get(3).getY()));
    }

    public ArrayList<Point> getBoardsPoints() {
        return boardsPoints;
    }

    @Override
    public ArrayList<Point> getResizePoints() {
        return resizePoints;
    }

    @Override
    public ArrayList<Point> getRotatePoints() {
        return rotatePoints;
    }

    public void calcHitboxPoints() {
    }
    public boolean isClickedOn(double x, double y) {
        return  (x < getBoardsPoints().get(2).getX()) && (x > getBoardsPoints().get(0).getX()) && (y < getBoardsPoints().get(2).getY()) && (y > getBoardsPoints().get(0).getY());
    }

    public Polyline clone() {
        Polyline newPolyline = new Polyline(getStrokeColor());
        for (int i = 0; i < getPoints().size(); i++) {
            newPolyline.addPoint(getPoints().get(i).clone());
        }
        return newPolyline;
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
