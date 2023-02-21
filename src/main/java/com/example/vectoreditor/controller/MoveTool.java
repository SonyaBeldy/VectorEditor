package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.BordersPainter;
import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.Polyline;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class MoveTool extends Tool implements ITool{

    BordersPainter bordersPainter;
    private final Point pressPoint;
    Figure beforeMoveFigure;

    public MoveTool(CanvasController canvasController) {
        super(canvasController);
        pressPoint = new Point(0,0);

    }

    public MoveTool(CanvasController canvasController, Point pressPoint) {
        super(canvasController);
        this.pressPoint = pressPoint;
        beforeMoveFigure = canvasController.getCurrentFigure().orElseThrow().clone();
        bordersPainter = new BordersPainter(canvasController.getDrawCanvas().getGraphicsContext2D());
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if(canvasController.getCurrentFigure().isEmpty()) {
            return;
        }
        double shiftX =  event.getX() - pressPoint.getX();
        double shiftY =  event.getY() - pressPoint.getY();

        canvasController.getCurrentFigure().get().move(new Point(shiftX, shiftY));
        canvasController.redrawAllFigures();

        pressPoint.setX(event.getX());
        pressPoint.setY(event.getY());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        canvasController.redrawAllFigures();
        bordersPainter.drawBoards(canvasController.getCurrentFigure().orElseThrow());

        canvasController.setCurrentTool(new SelectTool(canvasController));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

}
