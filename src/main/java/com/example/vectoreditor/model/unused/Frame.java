package com.example.vectoreditor.model.unused;

import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.figure.Figure;
import com.example.vectoreditor.model.figure.FigureDecorationData;
import com.example.vectoreditor.model.figure.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Frame  {

    Point centerTop;
    Point centerRight;
    Point centerBot;
    Point centerLeft;

    Figure figure;

    List<Point> hitboxPoints;

    public Frame(FigureDecorationData figureDecorationData, Figure figure) {
//        super(figureDecorationData);
        calcResizePoints();
        hitboxPoints = new ArrayList<>();
        calcHitBoxPoints();

        this.figure = figure;
    }

    private void calcHitBoxPoints() {
//        rotatePoints.clear();
//        double hitbox = 10;
//         hitboxPoints.add(new Point(getLeftTop().getX() - hitbox, getLeftTop().getY() + hitbox));
//
//         hitboxPoints.add(new Point(centerTop.getX(), centerTop.getY() + hitbox));
//
//         hitboxPoints.add(new Point(getRightTop().getX() + hitbox, getRightTop().getY() + hitbox));
//
//         hitboxPoints.add(new Point(centerLeft.getX() + hitbox, centerLeft.getY()));
//
//        hitboxPoints.add(new Point(getRightBot().getX() + hitbox, getRightBot().getY() - hitbox));
//
//        hitboxPoints.add(new Point(centerBot.getX(), centerBot.getY() - hitbox));
//
//        hitboxPoints.add(new Point(getLeftBot().getX() - hitbox, getLeftBot().getY() - hitbox));
//
//        hitboxPoints.add(new Point(centerLeft.getX() - hitbox, centerLeft.getY()));




//        centerTop = new Point((getRightTop().getX() + getLeftTop().getX())/2, getLeftTop().getY());
//        centerRight = new Point(getLeftTop().getX(), (getRightBot().getY() + getRightTop().getY())/2);
//        centerBot = new Point((getRightBot().getX() + getLeftBot().getX())/2 , getLeftBot().getY());
//        centerLeft = new Point(getRightBot().getX(), (getRightBot().getY()) + getRightTop().getY()/2);

//        hitbox2.getLeftTop().setX(getLeftTop().getX() - hitbox);
//        hitbox2.getLeftTop().setY(getLeftTop().getY() + hitbox);
//
//        hitbox2.getRightTop().setX(getRightTop().getX() + hitbox);
//        hitbox2.getRightTop().setY(getRightTop().getY() + hitbox);
//
//        hitbox2.getRightBot().setX(getRightBot().getX() + hitbox);
//        hitbox2.getRightBot().setY(getRightBot().getY() - hitbox);
//
//        hitbox2.getLeftBot().setX(getLeftBot().getX() - hitbox);
//        hitbox2.getLeftBot().setY(getLeftBot().getY() - hitbox);
//
//
//        hitbox2.getCenterTop().setX(getCenterTop().getX());
//        hitbox2.getCenterTop().setY(getLeftTop().getY() + hitbox);
//
//        hitbox2.getCenterRight().setX(getCenterTop().getX() + hitbox);
//        hitbox2.getCenterRight().setY(getLeftTop().getY());
//
//        hitbox2.getCenterBot().setX(getCenterTop().getX());
//        hitbox2.getCenterBot().setY(getLeftTop().getY() - hitbox);
//
//        hitbox2.getCenterLeft().setX(getCenterTop().getX() - hitbox);
//        hitbox2.getCenterLeft().setY(getLeftTop().getY());
    }
    private void calcResizePoints() {
//        setLeftTop(PointListUtils.calcMinX(figure.getPoints()) );

//        centerTop = new Point((getRightTop().getX() + getLeftTop().getX())/2, getLeftTop().getY());
//        centerRight = new Point(getLeftTop().getX(), (getRightBot().getY() + getRightTop().getY())/2);
//        centerBot = new Point((getRightBot().getX() + getLeftBot().getX())/2 , getLeftBot().getY());
//        centerLeft = new Point(getRightBot().getX(), (getRightBot().getY()) + getRightTop().getY()/2);
    }

    public List<Point> getHitboxPoints() {
        return hitboxPoints;
    }


    public Point getCenterTop() {
        return centerTop;
    }

    public void setCenterTop(Point centerTop) {
        this.centerTop = centerTop;
    }

    public Point getCenterRight() {
        return centerRight;
    }

    public void setCenterRight(Point centerRight) {
        this.centerRight = centerRight;
    }

    public Point getCenterBot() {
        return centerBot;
    }

    public void setCenterBot(Point centerBot) {
        this.centerBot = centerBot;
    }

    public Point getCenterLeft() {
        return centerLeft;
    }

    public void setCenterLeft(Point centerLeft) {
        this.centerLeft = centerLeft;
    }
}
