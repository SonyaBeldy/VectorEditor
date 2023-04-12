package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.SelectedFigureControllsList;
import com.example.vectoreditor.model.figures.Figure;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.ResizeDirection;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class ResizeTool extends SelectTool implements ITool {

//    Figure currentFigure;
//    Figure copyCurrentFigure;
    Point clickPoint = new Point(0, 0);
    Point dragPoint;
    Point oppositePoint;
    Point center;

    ResizeDirection direction;

    List<Figure> copyOfSelectedFigures;
    List<Figure> selectedFigures;
    SelectedFigureControllsList selectedFigureControllsList;

    public ResizeTool(MainController mainController, Point dragPoint, Point oppositePoint, ResizeDirection direction) {
        super(mainController);
//        currentFigure = mainController.getCurrentCanvasController().getCurrentFigureController().orElseThrow().getFigure();
        this.direction = direction;
        copyOfSelectedFigures = new ArrayList<>();

//        copyCurrentFigure = currentFigure.clone();
        selectedFigureControllsList = mainController.getCurrentCanvasController().getSelectedFiguresList();

        selectedFigures = selectedFigureControllsList.getAllFigures();
        copyOfSelectedFigures = selectedFigureControllsList.cloneFigures();

        this.dragPoint = dragPoint.clone();
        this.oppositePoint = oppositePoint.clone();

        center = selectedFigureControllsList.getCenter().clone();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        clickPoint.setX(event.getX());
        clickPoint.setY(event.getY());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        switch (direction) {
            case X -> resizeX(event, dragPoint, oppositePoint);
            case Y -> resizeY(event, dragPoint, oppositePoint);
            case XY -> {
                resizeX(event, dragPoint, oppositePoint);
                resizeY(event, dragPoint, oppositePoint);
            }
        }
        resizeBorders();

        selectedFigureControllsList.calcCenter();
//        currentFigure.calcCenter();

        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    private void resizeBorders() {

        for (int i = 0; i < selectedFigures.size(); i++) {
            Figure copyCurrentFigure = selectedFigureControllsList.getFigure(i).clone();
            double angle = copyCurrentFigure.getTransformProperties().getAngle();
            Point center = copyCurrentFigure.getCenter().clone();
            copyCurrentFigure.rotate(copyCurrentFigure.clone(), center, -angle);
            copyCurrentFigure.rotate(copyCurrentFigure.clone(), center, angle);
        }
    }

    private void resizeX(MouseEvent event, Point dragPoint, Point oppositePoint) {
        for (int i = 0; i < selectedFigures.size(); i++) {
            Figure currentFigure = selectedFigures.get(i);

            double angle = currentFigure.getTransformProperties().getAngle();

            dragPoint = dragPoint.clone();
            oppositePoint = oppositePoint.clone();

            dragPoint.rotate(center, -angle);
            oppositePoint.rotate(center, -angle);

            double dragX = dragPoint.getX();
            double oppositeX = oppositePoint.getX();

            Point e = new Point(event.getX(), event.getY());
            e.rotate(center, -angle);

            double ratioX = (e.getX() - oppositeX) / (dragX - oppositeX);

            Figure copyCurrentFigure = copyOfSelectedFigures.get(i);
            for (int j = 0; j < currentFigure.getPoints().size(); j++) {

                copyCurrentFigure.getPoints().get(j).rotate(copyCurrentFigure.getCenter(), -angle);
                double lengthX = copyCurrentFigure.getPoints().get(j).getX() - oppositeX;

                currentFigure.getPoints().get(j).rotate(center, -angle);
                currentFigure.getPoints().get(j).setX(oppositePoint.getX() + lengthX * ratioX);
                currentFigure.getPoints().get(j).rotate(center, angle);

                copyCurrentFigure.getPoints().get(j).rotate(copyCurrentFigure.getCenter(), angle);
            }
        }

    }

    private void resizeY(MouseEvent event, Point dragPoint, Point oppositePoint) {
        for (int i = 0; i < selectedFigures.size(); i++) {
            Figure currentFigure = selectedFigures.get(i);

            double angle = currentFigure.getTransformProperties().getAngle();

            dragPoint = dragPoint.clone();
            oppositePoint = oppositePoint.clone();

            dragPoint.rotate(center, -angle);
            oppositePoint.rotate(center, -angle);

            double dragY = dragPoint.getY();
            double oppositeY = oppositePoint.getY();

            Point e = new Point(event.getX(), event.getY());
            e.rotate(center, -angle);

            double ratioY = (e.getY() - oppositeY) / (dragY - oppositeY);

            Figure copyCurrentFigure = copyOfSelectedFigures.get(i);
            for (int j = 0; j < currentFigure.getPoints().size(); j++) {

                copyCurrentFigure.getPoints().get(j).rotate(copyCurrentFigure.getCenter(), -angle);
                double lengthY = copyCurrentFigure.getPoints().get(j).getY() - oppositeY;

                currentFigure.getPoints().get(j).rotate(center, -angle);
                currentFigure.getPoints().get(j).setY(oppositePoint.getY() + lengthY * ratioY);
                currentFigure.getPoints().get(j).rotate(center, angle);

                copyCurrentFigure.getPoints().get(j).rotate(copyCurrentFigure.getCenter(), angle);

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

}
