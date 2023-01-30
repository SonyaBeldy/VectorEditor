package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.*;
import javafx.scene.input.MouseEvent;

public class RotateTool extends SelectTool  implements ITool{

    private Point pressPoint;
    private Figure copyCurrentFigure;
    private double angle;

    private Point center;
    double a;
    private double clickAngle;

    public RotateTool(CanvasController canvasController) {
        super(canvasController);
//        pressPoint = new Point(0,0);
//        a = 0;
//        copyCurrentFigure = canvasController.getCurrentFigure().clone();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        copyCurrentFigure = canvasController.getCurrentFigure().clone();
        center = copyCurrentFigure.getCenter();
        pressPoint = new Point(event.getX(),event.getY());
        clickAngle = Math.atan2(event.getY() - center.getY(), event.getX() - center.getX());
//        pressPoint.setX(event.getX());
//        pressPoint.setY(event.getY());
//        center = ListUtils.getCenter(canvasController.getCurrentFigure().getBoardsPoints());
//        a = Math.atan2(event.getY() - center.getY(), event.getX() - center.getX());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
//        System.out.println("ca = " + clickAngle);
//        System.out.println("a = " + Math.atan2(event.getY() - center.getY(), event.getX() - center.getX()));
//        System.out.println("calc = " + (Math.atan2(event.getY() - center.getY(), event.getX() - center.getX()) - clickAngle));
        angle = Math.atan2(event.getY() - center.getY(), event.getX() - center.getX()) - clickAngle;
        if (angle > Math.PI) {
            angle = -2 * Math.PI + angle;
        }
        if (angle < -Math.PI) {
            angle = 2 * Math.PI + angle;
        }
        canvasController.getCurrentFigure().rotate(copyCurrentFigure, center, angle);
        canvasController.redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
//        canvasController.getCurrentFigure().setAngle(canvasController.getCurrentFigure().getAngle() + angle);
    }
}
