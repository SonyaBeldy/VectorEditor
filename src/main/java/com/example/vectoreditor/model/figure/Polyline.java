package com.example.vectoreditor.model.figure;

import com.example.vectoreditor.model.Cloneable;
import com.example.vectoreditor.model.drawer.PolylineDrawer;
import javafx.scene.paint.Color;

public class Polyline extends Figure implements Cloneable<Figure> {

    public Polyline(Color strokeColor) {
        super(strokeColor);
        drawer = new PolylineDrawer(points);
    }

    public Polyline clone() {
        Polyline newPolyline = new Polyline(getStrokeColor());

        newPolyline.setAngle(getAngle());
        newPolyline.setStrokeColor(getStrokeColor());
        newPolyline.getCenter().setX(getCenter().getX());
        newPolyline.getCenter().setY(getCenter().getY());

        for (int i = 0; i < getPoints().size(); i++) {
            newPolyline.addPoint(getPoints().get(i).clone());
        }
        for (int i = 0; i < getBoardsPoints().size(); i++) {
            newPolyline.getBoardsPoints().add(getBoardsPoints().get(i).clone());
        }
        for (int i = 0; i < getRotatePoints().size(); i++) {
            newPolyline.getRotatePoints().add(getRotatePoints().get(i).clone());
        }
        for (int i = 0; i < getResizePoints().size(); i++) {
            newPolyline.getResizePoints().add(getResizePoints().get(i).clone());
        }
        return newPolyline;
    }

    public void calcHitboxPoints() {

    }



}
