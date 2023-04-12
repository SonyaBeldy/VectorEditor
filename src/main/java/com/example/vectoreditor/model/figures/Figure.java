package com.example.vectoreditor.model.figures;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.Cloneable;
import com.example.vectoreditor.model.Frame;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.PointListUtils;
import com.example.vectoreditor.model.drawers.Drawer;
import com.example.vectoreditor.model.SingleFrame;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Figure implements Cloneable<Figure> {

    String name;
    ArrayList<Point> points;
    FigureDecorationData figureDecorationData;
    FigureTransformData transformProperties;

    boolean isSelected = false;

    protected final Point center;

    Drawer drawer;

    public Figure(String name, FigureDecorationData properties) {
        this.name = name;
        this.figureDecorationData = properties;
        points = new ArrayList<>();
        center = new Point(0,0);

        transformProperties = new FigureTransformData();
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
        transformProperties.setAngle(beforeRotateFigure.transformProperties.getAngle() + angle);
        rotate(getPoints(), beforeRotateFigure.getPoints(), center, angle);
    }

    private void rotate(ArrayList<Point> points, ArrayList<Point> beforeRotatePoints, Point center, double angle) {
        for (int i = 0; i < points.size(); i++) {
            Point rotatedPoint = beforeRotatePoints.get(i).clone();
            rotatedPoint.rotate(center, angle);
            points.set(i, rotatedPoint);
        }
    }

    public void move(Point difference) {
        Figure beforeMoveFigure = clone();
        movePoints(beforeMoveFigure.getPoints(), getPoints(), difference);
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

    public String getName() {
        return name;
    }

    public boolean isCross(Point eventPoint, Point firstEdgePoint, Point secondEdgePoint) {
        double screenWidth = 1000;

        double x1 = eventPoint.getX();
        double y1 = eventPoint.getY();
        double x2 = screenWidth;
        double y2 = y1;
        if (((x2 - x1) * (firstEdgePoint.getY() - y1) - (y2 - y1) * (firstEdgePoint.getX() - x1)) *
                ((x2 - x1) * (secondEdgePoint.getY() - y1) - (y2 - y1) * (secondEdgePoint.getX() - x1)) < 0 &&
                ((secondEdgePoint.getX() - firstEdgePoint.getX()) * (y1 - firstEdgePoint.getY()) - (secondEdgePoint.getY() - firstEdgePoint.getY()) * (x1 - secondEdgePoint.getX())) *
                        ((secondEdgePoint.getX() - firstEdgePoint.getX()) * (y2 - firstEdgePoint.getY()) - (secondEdgePoint.getY() - firstEdgePoint.getY()) * (x2 - firstEdgePoint.getX())) < 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isClickedOn(Point eventPoint) {
        double crossCount = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            if (isCross(eventPoint, points.get(i), points.get(i + 1))) {
                crossCount++;
            }
        }
        if (isCross(eventPoint, points.get(0), points.get(points.size() - 1))) {
            crossCount++;
        }
        return crossCount % 2 != 0;
    }

    public void calcCenter(){
        Frame frame = new SingleFrame(this);
        Point newCenter = PointListUtils.calcCenter(frame.getEdgesPoints());

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public abstract Figure clone();
}
