package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.*;
import javafx.scene.input.MouseEvent;

public class RotateTool extends SelectTool  implements ITool{

    private Figure copyCurrentFigure;
    private double angle;
    private Point center;
    private double clickAngle;

    private final Figure currentFigure;

    public RotateTool(CanvasController canvasController) {
        super(canvasController);
        currentFigure = canvasController.getCurrentFigure();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        copyCurrentFigure = canvasController.getCurrentFigure().clone();
        center = copyCurrentFigure.getCenter();
        clickAngle = Math.atan2(event.getY() - center.getY(), event.getX() - center.getX());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
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
    }
}
