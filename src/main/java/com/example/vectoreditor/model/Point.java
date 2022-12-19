package com.example.vectoreditor.model;

public class Point implements Cloneable<Point>{
    private double x;
    private double y;
    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point clone() {
        return new Point(x, y);
    }
}
