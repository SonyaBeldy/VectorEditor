package com.example.vectoreditor.model;

public class MathGeom {

    public static double distance(Point first, Point second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow(first.getY() - second.getY(), 2));
    }
}
