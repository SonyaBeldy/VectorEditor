package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.BordersPainter;
import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.Polyline;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class MoveTool extends Tool implements ITool{

    BordersPainter bordersPainter;


    private final ArrayList<Point> beforeMovePoints;
    private final Point pressPoint;

    public MoveTool(CanvasController canvasController) {
        super(canvasController);
        beforeMovePoints = canvasController.getCurrentFigure().clone().getPoints();
        pressPoint = new Point(0,0);
        bordersPainter = new BordersPainter(canvasController.getDrawCanvas().getGraphicsContext2D());
    }

    public MoveTool(CanvasController canvasController, Point pressPoint) {
        super(canvasController);
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
        for (int i = 0; i < canvasController.getCurrentFigure().getPoints().size(); i++) {
            double beforeMoveX = beforeMovePoints.get(i).getX();
            double beforeMoveY = beforeMovePoints.get(i).getY();

            canvasController.getCurrentFigure().getPoints().get(i).setX(beforeMoveX + differenceX);
            canvasController.getCurrentFigure().getPoints().get(i).setY(beforeMoveY + differenceY);
        }
        canvasController.redrawAllFigures();
        canvasController.getCurrentFigure().calcBoardsPoints();

        bordersPainter.drawBoards(canvasController.getCurrentFigure());

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
