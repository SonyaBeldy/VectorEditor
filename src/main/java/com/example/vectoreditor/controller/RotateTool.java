package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.*;
import com.example.vectoreditor.model.figures.Figure;
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
        copyCurrentFigure = mainController.getCurrentCanvasController().getCurrentFigureController().orElseThrow().getFigure().clone();
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
        mainController.getCurrentCanvasController().getCurrentFigureController().orElseThrow().getFigure().rotate(copyCurrentFigure, center, angle);
        mainController.getPropertiesBoxController().update();
        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }
}
