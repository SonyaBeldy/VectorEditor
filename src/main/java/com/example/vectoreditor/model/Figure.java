package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class Figure implements Cloneable<Figure>{

    ArrayList<Point> points;
    protected final ArrayList<Point> boardsPoints;
    protected final ArrayList<Point> rotatePoints;
    protected final ArrayList<Point> resizePoints;
    protected final ArrayList<Point> hitboxPoints;

    protected final Point center;
    protected double angle;
    protected Color strokeColor;

    IDrawer drawer;

    public Figure(Color strokeColor) {
        this.strokeColor = strokeColor;
        points = new ArrayList<>();
        boardsPoints = new ArrayList<>();
        hitboxPoints = new ArrayList<>();
        rotatePoints = new ArrayList<>();
        resizePoints = new ArrayList<>();
        center = new Point(0,0);
    }

    public void draw(GraphicsContext graphicsContext) {
        drawer.draw(graphicsContext, strokeColor);
    }

    public void highlight(GraphicsContext graphicsContext) {
        drawer.draw(graphicsContext, Color.MEDIUMSLATEBLUE);
    }

    public void rotate(Figure beforeRotateFigure, Point center, double angle) {
        setAngle(beforeRotateFigure.getAngle() + angle);
        rotate(getPoints(), beforeRotateFigure.getPoints(), center, angle);
        rotate(getBoardsPoints(), beforeRotateFigure.getBoardsPoints(), center, angle);
        rotate(getRotatePoints(), beforeRotateFigure.getRotatePoints(), center, angle);
        rotate(getResizePoints(), beforeRotateFigure.getResizePoints(), center, angle);
    }
    private void rotate(ArrayList<Point> points, ArrayList<Point> beforeRotatePoints, Point center, double angle) {
        for (int i = 0; i < points.size(); i++) {
            Point rotatedPoint = beforeRotatePoints.get(i).clone();
            rotatedPoint.rotate(center, angle);
            points.set(i, rotatedPoint);
        }
    }

    public void calcBoardsPoints() {
        boardsPoints.clear();

        double minX = PointListUtils.calcMinX(points);
        double minY = PointListUtils.calcMinY(points);
        double maxX = PointListUtils.calcMaxX(points);
        double maxY = PointListUtils.calcMaxY(points);

        boardsPoints.add(new Point(minX, minY));
        boardsPoints.add(new Point(maxX, minY));
        boardsPoints.add(new Point(maxX, maxY));
        boardsPoints.add(new Point(minX, maxY));

        calcResizePoints();
        calcRotatePoints();
        calcCenter();
    }

    public void move(Point difference) {
        Figure beforeMoveFigure = clone();
        movePoints(beforeMoveFigure.getPoints(), getPoints(), difference);
        movePoints(beforeMoveFigure.getBoardsPoints(), getBoardsPoints(), difference);
        movePoints(beforeMoveFigure.getRotatePoints(), getRotatePoints(), difference);
        movePoints(beforeMoveFigure.getResizePoints(), getResizePoints(), difference);
        calcCenter();
    }

    private void movePoints(ArrayList<Point> beforeMovePoints, ArrayList<Point> points, Point difference) {
        for (int i = 0; i < points.size(); i++) {
            double beforeMoveX = beforeMovePoints.get(i).getX();
            double beforeMoveY = beforeMovePoints.get(i).getY();

            points.get(i).setX(beforeMoveX + difference.getX());
            points.get(i).setY(beforeMoveY + difference.getY());
        }
    }
    private void calcRotatePoints() {
        rotatePoints.clear();
        double hitbox = 10;

        rotatePoints.add(new Point(boardsPoints.get(0).getX() - hitbox, boardsPoints.get(0).getY() - hitbox));
        rotatePoints.add(new Point(boardsPoints.get(1).getX() + hitbox, boardsPoints.get(1).getY() - hitbox));
        rotatePoints.add(new Point(boardsPoints.get(2).getX() + hitbox, boardsPoints.get(2).getY() + hitbox));
        rotatePoints.add(new Point(boardsPoints.get(3).getX() - hitbox, boardsPoints.get(3).getY() + hitbox));
    }
    private void calcResizePoints() {
        resizePoints.clear();

        resizePoints.add(new Point((boardsPoints.get(1).getX() - boardsPoints.get(0).getX())/2 + boardsPoints.get(0).getX(), boardsPoints.get(0).getY()));
        resizePoints.add(new Point(boardsPoints.get(1).getX(), (boardsPoints.get(1).getY() - boardsPoints.get(2).getY())/2 + boardsPoints.get(2).getY()));
        resizePoints.add(new Point((boardsPoints.get(2).getX() - boardsPoints.get(3).getX())/2 + boardsPoints.get(3).getX(), boardsPoints.get(2).getY()));
        resizePoints.add(new Point(boardsPoints.get(3).getX(), (boardsPoints.get(0).getY() - boardsPoints.get(3).getY())/2 + boardsPoints.get(3).getY()));
    }
    public ArrayList<Point> getBoardsPoints() {
        return boardsPoints;
    }
    public ArrayList<Point> getResizePoints() {
        return resizePoints;
    }
    public ArrayList<Point> getRotatePoints() {
        return rotatePoints;
    }


    public boolean isClickedOn(double x, double y) {
        return  (x < PointListUtils.calcMaxX(getBoardsPoints())) && (x > PointListUtils.calcMinX(getBoardsPoints())) && (y < PointListUtils.calcMaxY(getBoardsPoints())) && (y > PointListUtils.calcMinY(getBoardsPoints()));
    }

    public double getAngle() {
        return angle;
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getWidth() { return getBoardsPoints().get(2).getX() - getBoardsPoints().get(0).getX(); }

    public void setWidth(double width) {
        //resize
    }

    public void calcCenter(){
        Point newCenter = PointListUtils.calcCenter(getBoardsPoints());

        center.setX(newCenter.getX());
        center.setY(newCenter.getY());
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public Point getCenter() {
        return center;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public abstract Figure clone();
}
