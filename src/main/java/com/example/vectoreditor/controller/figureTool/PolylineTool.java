package com.example.vectoreditor.controller.figureTool;

import com.example.vectoreditor.controller.CanvasController;
import com.example.vectoreditor.controller.ITool;
import com.example.vectoreditor.controller.Tool;
import com.example.vectoreditor.model.*;
import com.example.vectoreditor.model.figure.Figure;
import com.example.vectoreditor.model.figure.Polyline;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Optional;

public class PolylineTool extends Tool implements ITool {

    private boolean isDrawing;

    public PolylineTool(CanvasController canvasController) {
        super(canvasController);
        isDrawing = false;
        figure = new Polyline(Color.BLACK);

    }

    public void createFigure() {
        figure = new Polyline(canvasController.getStrokeColor());
    }
    @Override
    public void mousePressed(MouseEvent event) {

        if (event.isPrimaryButtonDown()) {
            if (!isDrawing) {
                isDrawing = true;
                createFigure();
                figure.addPoint(new Point(event.getX(), event.getY()));
                figure.calcBoardsPoints();
                figure.calcCenter();
                canvasController.addFigure(figure);
                canvasController.setCurrentFigure(Optional.empty());
            }
            figure.addPoint(new Point(event.getX(), event.getY()));
        }

        if (event.isSecondaryButtonDown()) {
            isDrawing = false;
            if (figure.getPoints().size() > 2){
                figure.getPoints().remove(figure.getPoints().size() - 1);

                canvasController.redrawAllFigures();
                figure.calcBoardsPoints();
                figure.calcCenter();
                canvasController.setCurrentFigure(Optional.of(figure));
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

        figure.calcBoardsPoints();
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
            return figure.getPoints().get(figure.getPoints().size() - 1);
        }

}
