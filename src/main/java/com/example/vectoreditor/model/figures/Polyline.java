package com.example.vectoreditor.model.figures;

import com.example.vectoreditor.model.Cloneable;
import com.example.vectoreditor.model.drawers.PolylineDrawer;

public class Polyline extends Figure implements Cloneable<Figure> {

    public Polyline(FigureDecorationData properties) {
        super("Polyline", properties);
        drawer = new PolylineDrawer(points);
    }

    public Polyline clone() {
        Polyline newPolyline = new Polyline(getFigureDecorationData().clone());

        newPolyline.getCenter().setX(getCenter().getX());
        newPolyline.getCenter().setY(getCenter().getY());

        newPolyline.getTransformProperties().setAngle(transformProperties.getAngle());

        for (int i = 0; i < getPoints().size(); i++) {
            newPolyline.addPoint(getPoints().get(i).clone());
        }
        return newPolyline;
    }
}
