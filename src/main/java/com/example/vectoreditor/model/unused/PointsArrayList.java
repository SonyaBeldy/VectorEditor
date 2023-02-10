package com.example.vectoreditor.model.unused;

import com.example.vectoreditor.model.Point;

import java.util.ArrayList;

public class PointsArrayList<P> extends ArrayList<Point> {

    Point getNextTo(Point point) {
        int ind = getInd(point);
        if (ind == -1){
            return null;
        }
        if (ind == size() - 1) {
            return get(0);
        }
        return get(ind + 1);
    }

    Point getPreviousTo(Point point) {
        int ind = getInd(point);
        if (ind == -1){
            return null;
        }
        if (ind == 0) {
            return get(size() - 1);
        }
        return get(ind - 1);
    }

    private int getInd(Point point) {
        for (int i = 0; i < size(); i++) {
            if(point == get(i)) {
                return i;
            }
        }
        return -1;
    }


}
