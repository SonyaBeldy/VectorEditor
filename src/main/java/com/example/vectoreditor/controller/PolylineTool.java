package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PolylineTool extends Tool implements ITool{

    private boolean isDrawing;
    private Polyline polyline;

    public PolylineTool(CanvasController canvasController) {
        super(canvasController);
        isDrawing = false;
        polyline = new Polyline(Color.BLACK);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            if (!isDrawing) {
                isDrawing = true;
                polyline = new Polyline(Color.BLACK);
                polyline.addPoint(new Point(event.getX(), event.getY()));

                canvasController.getFigures().add(polyline);
            }
            polyline.addPoint(new Point(event.getX(), event.getY()));
        }

        if (event.isSecondaryButtonDown()) {
            isDrawing = false;
            if (polyline.getPoints().size() > 2){
                polyline.getPoints().remove(polyline.getPoints().size() - 1);

                canvasController.redrawAllFigures();
                polyline.calcBoardsPoints();
                polyline.drawBorders(drawCanvas.getGraphicsContext2D());

                currentFigure = polyline;
                canvasController.addFigure(currentFigure);
            } else {
                canvasController.getFigures().remove(canvasController.getFigures().size() - 1);
                canvasController.redrawAllFigures();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (!isDrawing) {
            return;
        }
        getLastPoint().setX(event.getX());
        getLastPoint().setY(event.getY());

        canvasController.redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (!isDrawing) {
            return;
        }
        getLastPoint().setX(event.getX());
        getLastPoint().setY(event.getY());

        polyline.calcBoardsPoints();
        canvasController.redrawAllFigures();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (isDrawing) {
            getLastPoint().setX(event.getX());
            getLastPoint().setY(event.getY());

            canvasController.redrawAllFigures();
        }
    }

        private Point getLastPoint() {
            return polyline.getPoints().get(polyline.getPoints().size() - 1);
        }

}
