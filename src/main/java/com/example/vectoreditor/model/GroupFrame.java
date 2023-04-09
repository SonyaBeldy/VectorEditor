package com.example.vectoreditor.model;

import com.example.vectoreditor.model.figures.Figure;

import java.util.List;

public class GroupFrame extends Frame {

    List<Figure> figures;

    public GroupFrame(List<Figure> figures) {
        this.figures = figures;
    }
    @Override
    public void update() {
        Figure notRotatedFigure = figures.get(0).clone();
        notRotatedFigure.rotate(figures.get(0).clone(), figures.get(0).getCenter(), -figures.get(0).getTransformProperties().getAngle());

        double generalMinX = PointListUtils.calcMinX(notRotatedFigure.getPoints());
        double generalMinY = PointListUtils.calcMinY(notRotatedFigure.getPoints());
        double generalMaxX = PointListUtils.calcMaxX(notRotatedFigure.getPoints());
        double generalMaxY = PointListUtils.calcMaxY(notRotatedFigure.getPoints());
        for (int i = 0; i < figures.size(); i++) {
            notRotatedFigure = figures.get(i).clone();
            notRotatedFigure.rotate(figures.get(i).clone(), figures.get(i).getCenter(), -figures.get(i).getTransformProperties().getAngle());
            double minX = PointListUtils.calcMinX(notRotatedFigure.getPoints());
            if (minX < generalMinX) {
                generalMinX = minX;
            }
            double minY = PointListUtils.calcMinY(notRotatedFigure.getPoints());
            if (minY < generalMinY) {
                generalMinY = minY;
            }
            double maxX = PointListUtils.calcMaxX(notRotatedFigure.getPoints());
            if (maxX > generalMaxX) {
                generalMaxX = maxX;
            }
            double maxY = PointListUtils.calcMaxY(notRotatedFigure.getPoints());
            if (maxY > generalMaxY) {
                generalMaxY = maxY;
            }
        }

        leftTop.change(generalMinX, generalMinY);
        leftBot.change(generalMinX, generalMaxY);
        rightTop.change(generalMaxX, generalMinY);
        rightBot.change(generalMaxX, generalMaxY);

        centerTop.change((generalMaxX + generalMinX)/2, generalMinY);
        centerBot.change((generalMaxX + generalMinX)/2, generalMaxX);
        centerRight.change(generalMaxX, (generalMaxY + generalMinY)/2);
        centerLeft.change(generalMinX, (generalMaxY + generalMinY)/2);

        center.change((generalMaxX + generalMinX)/2, (generalMaxY + generalMinY)/2);
    }

    @Override
    public boolean hitRotatePoint(Point eventPoint, Point framePoint, int shiftX, int shiftY) {
        return false;
    }

    private void updateForSeveral() {

    }
}
