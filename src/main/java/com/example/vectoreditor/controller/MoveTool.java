package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.BordersPainter;
import com.example.vectoreditor.model.figure.Figure;
import com.example.vectoreditor.model.Point;
import javafx.scene.input.MouseEvent;

public class MoveTool extends Tool implements ITool{

    BordersPainter bordersPainter;
    private final Point pressPoint;
    Figure beforeMoveFigure;

    public MoveTool(MainController mainController) {
        super(mainController);
        pressPoint = new Point(0,0);

    }

    public MoveTool(MainController mainController, Point pressPoint) {
        super(mainController);
        this.pressPoint = pressPoint;
        beforeMoveFigure = mainController.getCurrentCanvasController().getCurrentFigureController().orElseThrow().getFigure().clone();
        bordersPainter = new BordersPainter(mainController.getCurrentCanvasController().getDrawCanvas().getGraphicsContext2D());
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if(mainController.getCurrentCanvasController().getCurrentFigureController().isEmpty()) {
            return;
        }
        double shiftX =  event.getX() - pressPoint.getX();
        double shiftY =  event.getY() - pressPoint.getY();

        mainController.getCurrentCanvasController().getCurrentFigureController().get().getFigure().move(new Point(shiftX, shiftY));
        mainController.getCurrentCanvasController().redrawAllFigures();

        pressPoint.setX(event.getX());
        pressPoint.setY(event.getY());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        CanvasViewController currentCanvasController = mainController.getCurrentCanvasController();
        currentCanvasController.redrawAllFigures();
        bordersPainter.drawBoards(currentCanvasController.getCurrentFigureController().orElseThrow().getFigure(), mainController.getCurrentCanvasController().getCurrentLayer().getColor());

        mainController.setCurrentTool(new SelectTool(mainController));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

}
