package com.example.vectoreditor.model.figure;

import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.drawer.RectangleDrawer;
import javafx.scene.paint.Color;

import java.util.List;

public class Rectangle extends Polyline {

    private Point leftTop;
    private Point rightTop;
    private Point rightBot;
    private Point leftBot;
    public Rectangle(Color strokeColor) {
        super(strokeColor);
        name = "Rectangle";
        drawer = new RectangleDrawer(points);
    }

    public Rectangle(Color strokeColor, Point pressPoint) {
        super(strokeColor);
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
