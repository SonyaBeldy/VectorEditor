package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.Polyline;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class MoveTool extends Tool implements ITool{

    private final Figure currentFigure;
    private final ArrayList<Point> beforeMovePoints;

    private final Point pressPoint;

    public MoveTool(CanvasController canvasController) {
        super(canvasController);
        currentFigure = canvasController.getCurrentFigure();
        beforeMovePoints = canvasController.getCurrentFigure().clone().getPoints();
        pressPoint = new Point(0,0);
    }

    public MoveTool(CanvasController canvasController, Point pressPoint) {
        super(canvasController);
        currentFigure = canvasController.getCurrentFigure();
        beforeMovePoints = canvasController.getCurrentFigure().clone().getPoints();
        this.pressPoint = pressPoint;
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        double differenceX =  event.getX() - pressPoint.getX();
        double differenceY =  event.getY() - pressPoint.getY();
        for (int i = 0; i < currentFigure.getPoints().size(); i++) {
            double beforeMoveX = beforeMovePoints.get(i).getX();
            double beforeMoveY = beforeMovePoints.get(i).getY();

            currentFigure.getPoints().get(i).setX(beforeMoveX + differenceX);
            currentFigure.getPoints().get(i).setY(beforeMoveY + differenceY);
        }
        canvasController.redrawAllFigures();
        canvasController.getCurrentFigure().calcBoardsPoints();
        currentFigure.drawBorders(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        canvasController.redrawAllFigures();
        currentFigure.drawBorders(drawCanvas.getGraphicsContext2D());
        canvasController.setCurrentTool(new SelectTool(canvasController));

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }
}
