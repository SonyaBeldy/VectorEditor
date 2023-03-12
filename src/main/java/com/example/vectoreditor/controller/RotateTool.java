package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.*;
import com.example.vectoreditor.model.figure.Figure;
import javafx.scene.input.MouseEvent;

public class RotateTool extends SelectTool  implements ITool{

    private Figure copyCurrentFigure;
    private Point center;
    private double clickAngle;

    public RotateTool(MainController mainController) {
        super(mainController);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        copyCurrentFigure = mainController.getCurrentCanvasController().getCurrentFigure().orElseThrow().clone();
        center = copyCurrentFigure.getCenter();
        clickAngle = Math.atan2(event.getY() - center.getY(), event.getX() - center.getX());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        double angle = Math.atan2(event.getY() - center.getY(), event.getX() - center.getX()) - clickAngle;
        if (angle > Math.PI) {
            angle = -2 * Math.PI + angle;
        }
        if (angle < -Math.PI) {
            angle = 2 * Math.PI + angle;
        }
        mainController.getCurrentCanvasController().getCurrentFigure().orElseThrow().rotate(copyCurrentFigure, center, angle);
        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }
}
