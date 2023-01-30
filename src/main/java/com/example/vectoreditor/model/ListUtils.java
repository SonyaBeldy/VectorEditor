package com.example.vectoreditor.model;

import java.util.ArrayList;

public class ListUtils {

    public static Point getCenter(ArrayList<Point> points) {
        double centerX = (getMaxX(points) - getMinX(points))/2 + getMinX(points);
        double centerY = (getMaxY(points) - getMinY(points))/2 + getMinY(points);
        centerY = Math.floor(centerY);
        centerX = Math.floor(centerX);
        return new Point(centerX, centerY);
    }

    public static double getMinX(ArrayList<Point> points) {
        double minX = points.get(0).getX();
        for (Point point : points) {
            if (point.getX() < minX) {
                minX = point.getX();
            }
        }
        return minX;
    }

    public static double getMinY(ArrayList<Point> points) {
        double minY = points.get(0).getY();
        for (Point point : points) {
            if (point.getY() < minY) {
                minY = point.getY();
            }
        }
        return minY;
    }

    public static double getMaxX(ArrayList<Point> points) {
        double maxX = points.get(0).getX();
        for (Point point : points) {
            if (point.getX() > maxX) {
                maxX = point.getX();
            }
        }
        return maxX;
    }

    public static double getMaxY(ArrayList<Point> points) {
        double maxY = points.get(0).getY();
        for (Point point : points) {
            if (point.getY() > maxY) {
                maxY = point.getY();
            }
        }
        return maxY;
    }
}
