package com.example.vectoreditor.model;

import com.example.vectoreditor.model.figures.Figure;

import java.util.List;

public class GroupFrame extends Frame {

    List<Figure> figures;

    public GroupFrame(List<Figure> figures) {
        this.figures = figures;
        update();
    }
    @Override
    public void update() {

        double generalMinX = PointListUtils.calcMinX(figures.get(0).getPoints());
        double generalMinY = PointListUtils.calcMinY(figures.get(0).getPoints());
        double generalMaxX = PointListUtils.calcMaxX(figures.get(0).getPoints());
        double generalMaxY = PointListUtils.calcMaxY(figures.get(0).getPoints());
        for (Figure figure : figures) {
            double minX = PointListUtils.calcMinX(figure.getPoints());
            if (minX < generalMinX) {
                generalMinX = minX;
            }
            double minY = PointListUtils.calcMinY(figure.getPoints());
            if (minY < generalMinY) {
                generalMinY = minY;
            }
            double maxX = PointListUtils.calcMaxX(figure.getPoints());
            if (maxX > generalMaxX) {
                generalMaxX = maxX;
            }
            double maxY = PointListUtils.calcMaxY(figure.getPoints());
            if (maxY > generalMaxY) {
                generalMaxY = maxY;
            }
        }

        leftTop.change(generalMinX, generalMinY);
        leftBot.change(generalMinX, generalMaxY);
        rightTop.change(generalMaxX, generalMinY);
        rightBot.change(generalMaxX, generalMaxY);

        centerTop.change((generalMaxX + generalMinX)/2, generalMinY);
        centerBot.change((generalMaxX + generalMinX)/2, generalMaxY);
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
