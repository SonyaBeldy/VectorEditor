package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Line;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.Polyline;
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

            }
            polyline.addPoint(new Point(event.getX(), event.getY()));
        }

        if (event.isSecondaryButtonDown()) {
            isDrawing = false;
            if (!polyline.getPoints().isEmpty()){
                polyline.getPoints().remove(polyline.getPoints().size() - 1);
                currentFigure = polyline;
                canvasController.addFigure(currentFigure);
            }
        }
        canvasController.redrawAllFigures();
        polyline.draw(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (!isDrawing) {
            return;
        }
        getLastPoint().setX(event.getX());
        getLastPoint().setY(event.getY());

        canvasController.redrawAllFigures();
        polyline.draw(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (!isDrawing) {
            return;
        }
        getLastPoint().setX(event.getX());
        getLastPoint().setY(event.getY());

        canvasController.redrawAllFigures();
        polyline.draw(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (isDrawing) {
            getLastPoint().setX(event.getX());
            getLastPoint().setY(event.getY());

            canvasController.redrawAllFigures();
            polyline.draw(drawCanvas.getGraphicsContext2D());
        }
    }

        private Point getLastPoint() {
            return polyline.getPoints().get(polyline.getPoints().size() - 1);
        }

}
