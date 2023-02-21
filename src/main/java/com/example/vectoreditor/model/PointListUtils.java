package com.example.vectoreditor.model;

import java.util.ArrayList;

public class PointListUtils {

    public static double calcDist(Point p1, Point p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return Math.round(dist);
    }
    public static Point calcCenter(ArrayList<Point> points) {
        double centerX = (calcMaxX(points) - calcMinX(points))/2 + calcMinX(points);
        double centerY = (calcMaxY(points) - calcMinY(points))/2 + calcMinY(points);
        centerY = Math.floor(centerY);
        centerX = Math.floor(centerX);
        return new Point(centerX, centerY);
    }

    public static double calcMinX(ArrayList<Point> points) {
        double minX = points.get(0).getX();
        for (Point point : points) {
            if (point.getX() < minX) {
                minX = point.getX();
            }
        }
        return minX;
    }

    public static double calcMinY(ArrayList<Point> points) {
        double minY = points.get(0).getY();
        for (Point point : points) {
            if (point.getY() < minY) {
                minY = point.getY();
            }
        }
        return minY;
    }

    public static double calcMaxX(ArrayList<Point> points) {
        double maxX = points.get(0).getX();
        for (Point point : points) {
            if (point.getX() > maxX) {
                maxX = point.getX();
            }
        }
        return maxX;
    }

    public static double calcMaxY(ArrayList<Point> points) {
        double maxY = points.get(0).getY();
        for (Point point : points) {
            if (point.getY() > maxY) {
                maxY = point.getY();
            }
        }
        return maxY;
    }
}
