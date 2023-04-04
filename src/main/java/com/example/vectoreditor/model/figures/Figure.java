package com.example.vectoreditor.model.figures;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.Cloneable;
import com.example.vectoreditor.model.Frame;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.PointListUtils;
import com.example.vectoreditor.model.drawers.Drawer;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Figure implements Cloneable<Figure> {

    String name;
    ArrayList<Point> points;
    FigureDecorationData figureDecorationData;
    FigureTransformData transformProperties;
//    protected final ArrayList<Point> boardsPoints;
//    protected final ArrayList<Point> rotatePoints;
//    protected final ArrayList<Point> resizePoints;
//    protected final ArrayList<Point> hitboxPoints;

    protected final Point center;

    Drawer drawer;

    public Figure(String name, FigureDecorationData properties) {
        this.name = name;
        this.figureDecorationData = properties;
        points = new ArrayList<>();
//        boardsPoints = new ArrayList<>();
//        hitboxPoints = new ArrayList<>();
//        rotatePoints = new ArrayList<>();
//        resizePoints = new ArrayList<>();
        center = new Point(0,0);

        transformProperties = new FigureTransformData();

//        Frame2 frame = new Frame2(this);
    }

    public void draw(GraphicsContext graphicsContext) {
        drawer.draw(graphicsContext, transformProperties, figureDecorationData);
    }

    public void highlight(GraphicsContext graphicsContext) {
        drawer.draw(graphicsContext, transformProperties, figureDecorationData);
    }

    public FigureTransformData getTransformProperties() {
        return transformProperties;
    }

    public void rotate(Figure beforeRotateFigure, Point center, double angle) {
        transformProperties.setAngle(beforeRotateFigure.getTransformProperties().getAngle() + angle);
//        rotate(getPoints(), beforeRotateFigure.getPoints(), center, angle);
//        rotate(getBoardsPoints(), beforeRotateFigure.getBoardsPoints(), center, angle);
//        rotate(getRotatePoints(), beforeRotateFigure.getRotatePoints(), center, angle);
//        rotate(getResizePoints(), beforeRotateFigure.getResizePoints(), center, angle);
    }

    private void rotate(ArrayList<Point> points, ArrayList<Point> beforeRotatePoints, Point center, double angle) {
        for (int i = 0; i < points.size(); i++) {
            Point rotatedPoint = beforeRotatePoints.get(i).clone();
            rotatedPoint.rotate(center, angle);
            points.set(i, rotatedPoint);
        }
    }
//    public void calcBoardsPoints() {
//        boardsPoints.clear();
//
//        double minX = PointListUtils.calcMinX(points);
//        double minY = PointListUtils.calcMinY(points);
//        double maxX = PointListUtils.calcMaxX(points);
//        double maxY = PointListUtils.calcMaxY(points);
//
////        frame.getLeftTop().setX(minX);
////        frame.getLeftTop().setY(minY);
////
////        frame.getRightTop().setX(maxX);
////        frame.getRightTop().setY(minY);
////
////        frame.getRightBot().setX(maxX);
////        frame.getRightBot().setY(maxY);
////
////        frame.getLeftBot().setX(minX);
////        frame.getLeftBot().setY(maxY);
//
//        boardsPoints.add(new Point(minX, minY));
//        boardsPoints.add(new Point(maxX, minY));
//        boardsPoints.add(new Point(maxX, maxY));
//        boardsPoints.add(new Point(minX, maxY));
//
//        calcResizePoints();
//        calcRotatePoints();
//        calcCenter();
//    }

    public void move(Point difference) {
        Figure beforeMoveFigure = clone();
        movePoints(beforeMoveFigure.getPoints(), getPoints(), difference);
//        movePoints(beforeMoveFigure.getBoardsPoints(), getBoardsPoints(), difference);
//        movePoints(beforeMoveFigure.getRotatePoints(), getRotatePoints(), difference);
//        movePoints(beforeMoveFigure.getResizePoints(), getResizePoints(), difference);
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

//    private void calcRotatePoints() {
//        rotatePoints.clear();
//        double hitbox = 10;
//
//        rotatePoints.add(new Point(boardsPoints.get(0).getX() - hitbox, boardsPoints.get(0).getY() - hitbox));
//        rotatePoints.add(new Point(boardsPoints.get(1).getX() + hitbox, boardsPoints.get(1).getY() - hitbox));
//        rotatePoints.add(new Point(boardsPoints.get(2).getX() + hitbox, boardsPoints.get(2).getY() + hitbox));
//        rotatePoints.add(new Point(boardsPoints.get(3).getX() - hitbox, boardsPoints.get(3).getY() + hitbox));
//    }
//    private void calcResizePoints() {
//        resizePoints.clear();
//
//        resizePoints.add(new Point((boardsPoints.get(1).getX() - boardsPoints.get(0).getX())/2 + boardsPoints.get(0).getX(), boardsPoints.get(0).getY()));
//        resizePoints.add(new Point(boardsPoints.get(1).getX(), (boardsPoints.get(1).getY() - boardsPoints.get(2).getY())/2 + boardsPoints.get(2).getY()));
//        resizePoints.add(new Point((boardsPoints.get(2).getX() - boardsPoints.get(3).getX())/2 + boardsPoints.get(3).getX(), boardsPoints.get(2).getY()));
//        resizePoints.add(new Point(boardsPoints.get(3).getX(), (boardsPoints.get(0).getY() - boardsPoints.get(3).getY())/2 + boardsPoints.get(3).getY()));
//    }

    public String getName() {
        return name;
    }
//    public ArrayList<Point> getBoardsPoints() {
//        return boardsPoints;
//    }
//    public ArrayList<Point> getResizePoints() {
//        return resizePoints;
//    }
//    public ArrayList<Point> getRotatePoints() {
//        return rotatePoints;
//    }


    public boolean isClickedOn(double x, double y) {
//        return  (x < PointListUtils.calcMaxX(getBoardsPoints())) && (x > PointListUtils.calcMinX(getBoardsPoints())) && (y < PointListUtils.calcMaxY(getBoardsPoints())) && (y > PointListUtils.calcMinY(getBoardsPoints()));
//        return  (x < PointListUtils.calcMaxX(getFrame().getEdgesPoints())) && (x > PointListUtils.calcMinX(getFrame().getEdgesPoints()))
//                && (y < PointListUtils.calcMaxY(getFrame().getEdgesPoints())) && (y > PointListUtils.calcMinY(getFrame().getEdgesPoints()));
        return false;
    }

//    public double getWidth() {
//        double x1 = getBoardsPoints().get(0).getX();
//        double y1 = getBoardsPoints().get(0).getY();
//        double x2 = getBoardsPoints().get(2).getX();
//        double y2 = getBoardsPoints().get(2).getY();
//        double width = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
//        return Math.round(width);
//    }

    public void calcCenter(){
        Frame frame2 = new Frame(this);
        Point newCenter = PointListUtils.calcCenter(frame2.getEdgesPoints());

        center.setX(newCenter.getX());
        center.setY(newCenter.getY());
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public FigureDecorationData getFigureDecorationData() {
        return figureDecorationData;
    }

    public Point getCenter() {
        return center;
    }

    public abstract Figure clone();
}
