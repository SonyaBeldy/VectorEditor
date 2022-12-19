package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.Point;
import javafx.scene.input.MouseEvent;

public class SelectTool extends Tool implements ITool{

    Figure currentFigure;
    public SelectTool(CanvasController canvasController){
        super(canvasController);
        currentFigure = canvasController.getCurrentFigure();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        canvasController.setCurrentFigure(canvasController.whatWasClickedOn(event));

        if (canvasController.getCurrentFigure() == null) {
            return;
        }
        canvasController.redrawAllFigures();
        currentFigure.drawBorders(drawCanvas.getGraphicsContext2D());
        canvasController.setCurrentTool(new MoveTool(canvasController, new Point(event.getX(), event.getY())));
    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (currentFigure == null) {
            return;
        }
        canvasController.redrawAllFigures();
        canvasController.getCurrentFigure().drawBorders(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (canvasController.getFigures().isEmpty()) {
            return;
        }
        canvasController.redrawAllFigures();
        Figure enteredFigure = canvasController.whatWasClickedOn(event);
        if (enteredFigure != null) {
            enteredFigure.highlight(drawCanvas.getGraphicsContext2D());
        }
        if (currentFigure != null) {
            currentFigure.drawBorders(drawCanvas.getGraphicsContext2D());
        }
    }
}
