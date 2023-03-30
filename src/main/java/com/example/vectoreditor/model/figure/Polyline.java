package com.example.vectoreditor.model.figure;

import com.example.vectoreditor.model.Cloneable;
import com.example.vectoreditor.model.drawer.PolylineDrawer;

public class Polyline extends Figure implements Cloneable<Figure> {

    public Polyline(FigureDecorationData properties) {
        super("Polyline", properties);
        drawer = new PolylineDrawer(points);
    }

    public Polyline clone() {
        Polyline newPolyline = new Polyline(getFigureDecorationData().clone());

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
