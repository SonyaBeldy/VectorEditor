package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Line;
import javafx.scene.input.MouseEvent;

public class LineTool extends Tool implements ITool{

    public LineTool(CanvasController canvasController){
        super(canvasController);
    }
    @Override
    public void mousePressed(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        currentFigure = new Line(x,y, x, y, canvasController.getStrokeColor());
        currentFigure.draw(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        currentFigure.setEndPoint(x, y);
        canvasController.redrawAllFigures();

        currentFigure.draw(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        canvasController.redrawAllFigures();
        currentFigure.setEndPoint(x, y);
        currentFigure.draw(drawCanvas.getGraphicsContext2D());

        currentFigure.calcBoardsPoints();
        currentFigure.drawBorders(drawCanvas.getGraphicsContext2D());

        canvasController.addFigure(currentFigure);
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

}
