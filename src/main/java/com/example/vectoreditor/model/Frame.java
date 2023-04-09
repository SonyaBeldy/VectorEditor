package com.example.vectoreditor.model;

import com.example.vectoreditor.model.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    protected final Point leftTop = new Point();
    protected final Point leftBot = new Point();
    protected final Point rightTop = new Point();
    protected final Point rightBot = new Point();
    protected final Point centerTop = new Point();
    protected final Point centerRight = new Point();
    protected final Point centerBot = new Point();
    protected final Point centerLeft = new Point();

    private int rotateShift = 10;
    protected final Point center = new Point();

    public abstract void update();

    protected void rotate(double angle) {
        for (Point point : List.of(leftTop, leftBot, rightTop, rightBot, centerTop, centerBot, centerLeft, centerRight)) {
            point.rotate(center, angle);
        }
    }
//    private void calcRotateHitboxPoints() {
//        rotateHitboxPoints.clear();
//        double hitbox = 10;
//         rotateHitboxPoints.add(new Point(leftTop.getX() - hitbox, leftTop.getY() + hitbox));
//
//         rotateHitboxPoints.add(new Point(centerTop.getX(), centerTop.getY() + hitbox));
//
//         rotateHitboxPoints.add(new Point(rightTop.getX() + hitbox, rightTop.getY() + hitbox));
//
//         rotateHitboxPoints.add(new Point(centerLeft.getX() + hitbox, centerLeft.getY()));
//
//        rotateHitboxPoints.add(new Point(rightBot.getX() + hitbox, rightBot.getY() - hitbox));
//
//        rotateHitboxPoints.add(new Point(centerBot.getX(), centerBot.getY() - hitbox));
//
//        rotateHitboxPoints.add(new Point(leftBot.getX() - hitbox, leftBot.getY() - hitbox));
//
//        rotateHitboxPoints.add(new Point(centerLeft.getX() - hitbox, centerLeft.getY()));

//    }

    public boolean hitLeftTop(Point eventPoint) {
        return in(eventPoint, leftTop);
    }

    public boolean hitRightTop(Point eventPoint) {
        return in(eventPoint, rightTop);
    }

    public boolean hitRightBot(Point eventPoint) {
        return in(eventPoint, rightBot);
    }

    public boolean hitLeftBot(Point eventPoint) {
        return in(eventPoint, leftBot);
    }

    public boolean hitCenterTop(Point eventPoint) {
        return in(eventPoint, centerTop);
    }

    public boolean hitCenterRight(Point eventPoint) {
        return in(eventPoint, centerRight);
    }

    public boolean hitCenterBot(Point eventPoint) {
        return in(eventPoint, centerBot);
    }

    public boolean hitCenterLeft(Point eventPoint) {
        return in(eventPoint, centerLeft);
    }
    public boolean hitLeftTopRotate(Point eventPoint) {
        return hitRotatePoint(eventPoint, leftTop, -rotateShift, -rotateShift);
    }
    public boolean hitRightTopRotate(Point eventPoint) {
        return hitRotatePoint(eventPoint, rightTop, rotateShift, -rotateShift);
    }
    public boolean hitRightBotRotate(Point eventPoint) {
        return hitRotatePoint(eventPoint, rightBot, rotateShift, rotateShift);
    }
    public boolean hitLeftBotRotate(Point eventPoint) {
        return hitRotatePoint(eventPoint, leftBot, -rotateShift, rotateShift);
    }
    public boolean hitCenterTopRotate(Point eventPoint) {
        return hitRotatePoint(eventPoint, centerTop, 0, -rotateShift);
    }
    public boolean hitCenterRightRotate(Point eventPoint) {
        return hitRotatePoint(eventPoint, centerRight, rotateShift, 0);
    }
    public boolean hitCenterBotRotate(Point eventPoint) {
        return hitRotatePoint(eventPoint, centerBot, 0, rotateShift);
    }

    public boolean hitCenterLeftRotate(Point eventPoint) {
        return hitRotatePoint(eventPoint, centerLeft, -rotateShift, 0);
    }

    public abstract boolean hitRotatePoint(Point eventPoint, Point framePoint, int shiftX, int shiftY);

    protected boolean in(Point eventPoint, Point framePoint) {
        double POINT_HITBOX = 40;
        return Math.pow(framePoint.getX() - eventPoint.getX(), 2) + Math.pow(framePoint.getY() - eventPoint.getY(), 2) <= POINT_HITBOX;
    }

    public List<Point> getEdgesPoints() {
        return List.of(leftTop, rightTop, rightBot, leftBot);
    }

    public List<Point> getPivotsPoints() {
        return List.of(leftTop, centerTop, rightTop, centerRight, rightBot, centerBot, leftBot, centerLeft);
    }
    public Point getCenter() {
        return center;
    }

    public Point getPointHitbox(Point point, double angle) {
        double hitbox = 10;
        return new Point(hitbox * Math.sin(angle), hitbox * Math.cos(angle));
    }
//    public void draw(GraphicsContext graphicsContext) {
//        graphicsContext.setFill(Color.RED);
//        for (Point point: List.of(leftTop, leftBot, rightTop, rightBot, centerBot, centerTop, centerLeft, centerRight)) {
//            graphicsContext.fillOval(point.getX() - 5, point.getY() - 5,10, 10);
//        }
//    }

//    public void move(Point difference) {
//        Figure beforeMoveFigure = figure.clone();
//        figure.calcCenter();
//    }
//    private void movePoints(List<Point> beforeMovePoints, List<Point> points, Point difference) {
//        for (int i = 0; i < getPivotsPoints().size(); i++) {
//            double beforeMoveX = beforeMovePoints.get(i).getX();
//            double beforeMoveY = beforeMovePoints.get(i).getY();
//
//            points.get(i).setX(beforeMoveX + difference.getX());
//            points.get(i).setY(beforeMoveY + difference.getY());
//        }
//    }
    public Point getLeftTop() {
        return leftTop;
    }

    public Point getLeftBot() {
        return leftBot;
    }

    public Point getRightTop() {
        return rightTop;
    }

    public Point getRightBot() {
        return rightBot;
    }

    public Point getCenterTop() {
        return centerTop;
    }

    public Point getCenterRight() {
        return centerRight;
    }

    public Point getCenterBot() {
        return centerBot;
    }

    public Point getCenterLeft() {
        return centerLeft;
    }
}
