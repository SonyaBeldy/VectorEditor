package com.example.vectoreditor.model.figure;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.drawer.RectangleDrawer;

import java.util.List;

public class Rectangle extends Polyline {

    private Point leftTop = new Point();
    private Point rightTop = new Point();
    private Point rightBot = new Point();
    private Point leftBot = new Point();
    public Rectangle(FigureDecorationData figureDecorationData) {
        super(figureDecorationData);
        name = "Rectangle";
        drawer = new RectangleDrawer(points);
        points.addAll(List.of(leftTop, rightTop, rightBot, leftBot));
    }

    public Rectangle(FigureDecorationData figureDecorationData, Point pressPoint) {
        super(figureDecorationData);
        name = "Rectangle";
        drawer = new RectangleDrawer(points);

        this.leftTop = pressPoint.clone();
        this.rightTop = pressPoint.clone();
        this.rightBot = pressPoint.clone();
        this.leftBot = pressPoint.clone();
        points.addAll(List.of(leftTop, rightTop, rightBot, leftBot));
    }

    public Point getLeftTop() {
        return leftTop;
    }

    public void setLeftTop(Point leftTop) {
        this.leftTop = leftTop;
    }

    public Point getRightTop() {
        return rightTop;
    }

    public void setRightTop(Point rightTop) {
        this.rightTop = rightTop;
    }

    public Point getRightBot() {
        return rightBot;
    }

    public void setRightBot(Point rightBot) {
        this.rightBot = rightBot;
    }

    public Point getLeftBot() {
        return leftBot;
    }

    public void setLeftBot(Point leftBot) {
        this.leftBot = leftBot;
    }
}
