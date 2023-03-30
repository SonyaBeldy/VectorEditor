package com.example.vectoreditor.model;

public class Point implements Cloneable<Point>{
    private double x;
    private double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point() {}

    public void rotate(Point center, double angle) {
        double x0 = center.getX() + (x - center.getX())*Math.cos(angle) - (y - center.getY())*Math.sin(angle);
        double y0 = center.getY() + (x - center.getX())*Math.sin(angle) + (y - center.getY())*Math.cos(angle);
        x = x0;
        y = y0;
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
