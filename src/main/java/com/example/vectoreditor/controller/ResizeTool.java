package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.figure.Figure;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.ResizeDirection;
import javafx.scene.input.MouseEvent;

public class ResizeTool extends SelectTool implements ITool {

    Figure currentFigure;
    Figure copyCurrentFigure;
    Point clickPoint = new Point(0, 0);
    Point dragPoint;
    Point oppositePoint;
    Point center;

    ResizeDirection direction;

    public ResizeTool(MainController mainController, Point dragPoint, Point oppositePoint, ResizeDirection direction) {
        super(mainController);
        currentFigure = mainController.getCurrentCanvasController().getCurrentFigureController().orElseThrow().getFigure();
        this.direction = direction;

        copyCurrentFigure = currentFigure.clone();
        this.dragPoint = dragPoint.clone();
        this.oppositePoint = oppositePoint.clone();
        center = currentFigure.getCenter().clone();
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
        currentFigure.calcCenter();
        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    //no
//    private void resize(MouseEvent event, Point dragPoint, Point oppositePoint) {
//        double angle = currentFigure.getTransformProperties().getAngle();
//
//        dragPoint = dragPoint.clone();
//        oppositePoint = oppositePoint.clone();
//
//        dragPoint.rotate(currentFigure.getCenter(), -angle);
//        oppositePoint.rotate(currentFigure.getCenter(), -angle);
//
//        double dragX = dragPoint.getX();
//        double oppositeX = oppositePoint.getX();
//
//        double dragY = dragPoint.getY();
//        double oppositeY = oppositePoint.getY();
//
//
//        Point e = new Point(event.getX(), event.getY());
//        e.rotate(currentFigure.getCenter(), -angle);
//
//        double ratioX = (e.getX() - oppositeX) / (dragX - oppositeX);
//        double ratioY = (e.getY() - oppositeY) / (dragY - oppositeY);
//
//        Point b = currentFigure.getFrame().getEdgesPoints().get(2).clone();
//        b.rotate(currentFigure.getCenter(), -angle);
//        for (int i = 0; i < currentFigure.getPoints().size(); i++) {
//
//            copyCurrentFigure.getPoints().get(i).rotate(copyCurrentFigure.getCenter(), -angle);
//            double lengthX = copyCurrentFigure.getPoints().get(i).getX() - oppositeX;
//            double lengthY = copyCurrentFigure.getPoints().get(i).getY() - oppositeY;
//
//            currentFigure.getPoints().get(i).rotate(currentFigure.getCenter(), -angle);
//
//            currentFigure.getPoints().get(i).setX(oppositePoint.getX() + lengthX * ratioX);
//            currentFigure.getPoints().get(i).setY(oppositePoint.getY() + lengthY * ratioY);
//
//            currentFigure.getPoints().get(i).rotate(currentFigure.getCenter(), angle);
//            copyCurrentFigure.getPoints().get(i).rotate(copyCurrentFigure.getCenter(), angle);
//
//        }
//    }

    private void resizeBorders() {
        Figure copyCurrentFigure = currentFigure.clone();
        double angle = copyCurrentFigure.getTransformProperties().getAngle();
        Point center = copyCurrentFigure.getCenter().clone();
        copyCurrentFigure.rotate(copyCurrentFigure.clone(), center, -angle);
//        copyCurrentFigure.calcBoardsPoints();
//        copyCurrentFigure.getFrame().update();
        copyCurrentFigure.rotate(copyCurrentFigure.clone(), center, angle);

//        currentFigure.getBoardsPoints().clear();
//        currentFigure.getBoardsPoints().addAll(copyCurrentFigure.getBoardsPoints());
//
//        currentFigure.getRotatePoints().clear();
//        currentFigure.getRotatePoints().addAll(copyCurrentFigure.getRotatePoints());
//
//        currentFigure.getResizePoints().clear();
//        currentFigure.getResizePoints().addAll(copyCurrentFigure.getResizePoints());
    }

    private void resizeX(MouseEvent event, Point dragPoint, Point oppositePoint) {
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

        for (int i = 0; i < currentFigure.getPoints().size(); i++) {

            copyCurrentFigure.getPoints().get(i).rotate(copyCurrentFigure.getCenter(), -angle);
            double lengthX = copyCurrentFigure.getPoints().get(i).getX() - oppositeX;

            currentFigure.getPoints().get(i).rotate(center, -angle);
            currentFigure.getPoints().get(i).setX(oppositePoint.getX() + lengthX * ratioX);
            currentFigure.getPoints().get(i).rotate(center, angle);

            copyCurrentFigure.getPoints().get(i).rotate(copyCurrentFigure.getCenter(), angle);
        }
    }

    private void resizeY(MouseEvent event, Point dragPoint, Point oppositePoint) {
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

        for (int i = 0; i < currentFigure.getPoints().size(); i++) {

            copyCurrentFigure.getPoints().get(i).rotate(copyCurrentFigure.getCenter(), -angle);
            double lengthY = copyCurrentFigure.getPoints().get(i).getY() - oppositeY;

            currentFigure.getPoints().get(i).rotate(center, -angle);
            currentFigure.getPoints().get(i).setY(oppositePoint.getY() + lengthY * ratioY);
            currentFigure.getPoints().get(i).rotate(center, angle);

            copyCurrentFigure.getPoints().get(i).rotate(copyCurrentFigure.getCenter(), angle);

        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

}
