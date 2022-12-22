package com.example.vectoreditor.model;

import java.util.ArrayList;

public class ListUtils {

    static double getMinX(ArrayList<Point> points) {
        double minX = points.get(0).getX();
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getX() < minX){
                minX = points.get(i).getX();
            }
        }
        return minX;
    }

    static double getMinY(ArrayList<Point> points) {
        double minY = points.get(0).getY();
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getY() < minY){
                minY = points.get(i).getY();
            }
        }
        return minY;
    }

    static double getMaxX(ArrayList<Point> points) {
        double maxX = points.get(0).getX();
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getX() > maxX){
                maxX = points.get(i).getX();
            }
        }
        return maxX;
    }

    static double getMaxY(ArrayList<Point> points) {
        double maxY = points.get(0).getY();
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getY() > maxY){
                maxY = points.get(i).getY();
            }
        }
        return maxY;
    }
}
