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
        beforeMoveFigure = canvasController.getCurrentFigure().clone();
        bordersPainter = new BordersPainter(canvasController.getDrawCanvas().getGraphicsContext2D());
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        double differenceX =  event.getX() - pressPoint.getX();
        double differenceY =  event.getY() - pressPoint.getY();

        canvasController.getCurrentFigure().move(beforeMoveFigure, new Point(differenceX, differenceY));
        canvasController.redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        canvasController.redrawAllFigures();
        bordersPainter.drawBoards(canvasController.getCurrentFigure());

        canvasController.setCurrentTool(new SelectTool(canvasController));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

}
