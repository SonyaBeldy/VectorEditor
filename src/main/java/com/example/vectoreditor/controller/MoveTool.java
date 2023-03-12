package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.BordersPainter;
import com.example.vectoreditor.model.figure.Figure;
import com.example.vectoreditor.model.Point;
import javafx.scene.input.MouseEvent;

public class MoveTool extends Tool implements ITool{

    BordersPainter bordersPainter;
    private final Point pressPoint;
    Figure beforeMoveFigure;

    public MoveTool(ScrollPaneController currentCanvasController) {
        super(currentCanvasController);
        pressPoint = new Point(0,0);

    }

    public MoveTool(ScrollPaneController currentCanvasController, Point pressPoint) {
        super(currentCanvasController);
        this.pressPoint = pressPoint;
        beforeMoveFigure = currentCanvasController.getCurrentFigure().orElseThrow().clone();
        bordersPainter = new BordersPainter(currentCanvasController.getDrawCanvas().getGraphicsContext2D());
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if(currentCanvasController.getCurrentFigure().isEmpty()) {
            return;
        }
        double shiftX =  event.getX() - pressPoint.getX();
        double shiftY =  event.getY() - pressPoint.getY();

        currentCanvasController.getCurrentFigure().get().move(new Point(shiftX, shiftY));
        currentCanvasController.redrawAllFigures();

        pressPoint.setX(event.getX());
        pressPoint.setY(event.getY());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        currentCanvasController.redrawAllFigures();
        bordersPainter.drawBoards(currentCanvasController.getCurrentFigure().orElseThrow());

        currentCanvasController.setCurrentTool(new SelectTool(currentCanvasController));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

}
