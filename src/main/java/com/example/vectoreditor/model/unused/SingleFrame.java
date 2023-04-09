package com.example.vectoreditor.model.unused;

import com.example.vectoreditor.model.Frame;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.PointListUtils;
import com.example.vectoreditor.model.figures.Figure;

import java.util.List;

public class SingleFrame extends Frame {
    private final Figure figure;

    public SingleFrame(Figure figure) {
        this.figure = figure;
        update();
    }

    @Override
    public void update() {
        Figure notRotatedFigure = figure.clone();
        notRotatedFigure.rotate(figure.clone(), figure.getCenter(), -figure.getTransformProperties().getAngle());

        double minX = PointListUtils.calcMinX(notRotatedFigure.getPoints());
        double minY = PointListUtils.calcMinY(notRotatedFigure.getPoints());
        double maxX = PointListUtils.calcMaxX(notRotatedFigure.getPoints());
        double maxY = PointListUtils.calcMaxY(notRotatedFigure.getPoints());

        leftTop.change(minX, minY);
        leftBot.change(minX, maxY);
        rightTop.change(maxX, minY);
        rightBot.change(maxX, maxY);

        centerTop.change((maxX + minX)/2, minY);
        centerBot.change((maxX + minX)/2, maxY);
        centerRight.change(maxX, (maxY + minY)/2);
        centerLeft.change(minX, (maxY + minY)/2);

        center.change((maxX + minX)/2, (maxY + minY)/2);

        rotate(figure.getTransformProperties().getAngle());
    }

    @Override
    public boolean hitRotatePoint(Point eventPoint, Point framePoint, int shiftX, int shiftY) {
        Point point = new Point(framePoint.getX() + shiftX, framePoint.getY() + shiftY);
        point.rotate(framePoint, figure.getTransformProperties().getAngle());
        return in(eventPoint, point);
    }

}
