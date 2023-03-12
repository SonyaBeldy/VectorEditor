package com.example.vectoreditor.controller.figureTool;

import com.example.vectoreditor.controller.CanvasController;
import com.example.vectoreditor.controller.ITool;
import com.example.vectoreditor.controller.ScrollPaneController;
import com.example.vectoreditor.controller.Tool;
import com.example.vectoreditor.model.*;
import com.example.vectoreditor.model.figure.Polyline;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Optional;

public class PolylineTool extends Tool implements ITool {

    private boolean isDrawing;

    public PolylineTool(ScrollPaneController currentCanvasController) {
        super(currentCanvasController);
        isDrawing = false;
        figure = new Polyline(Color.BLACK);

    }

    public void createFigure() {
        figure = new Polyline(currentCanvasController.getStrokeColor());
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
                currentCanvasController.addFigure(figure);
                currentCanvasController.setCurrentFigure(Optional.empty());
            }
            figure.addPoint(new Point(event.getX(), event.getY()));
        }

        if (event.isSecondaryButtonDown()) {
            isDrawing = false;
            if (figure.getPoints().size() > 2){
                figure.getPoints().remove(figure.getPoints().size() - 1);

                currentCanvasController.redrawAllFigures();
                figure.calcBoardsPoints();
                figure.calcCenter();
                currentCanvasController.setCurrentFigure(Optional.of(figure));
            } else {
                if (currentCanvasController.getCurrentLayer().isPresent()) {
                    currentCanvasController.getCurrentLayer().get().removeFigure(currentCanvasController.getCurrentLayer().get().getLayer().getObjectsCount() - 1);
                }
                currentCanvasController.redrawAllFigures();
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

        currentCanvasController.redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (!isDrawing) {
            return;
        }
        getLastPoint().setX(event.getX());
        getLastPoint().setY(event.getY());

        figure.calcBoardsPoints();
        currentCanvasController.redrawAllFigures();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (isDrawing) {
            getLastPoint().setX(event.getX());
            getLastPoint().setY(event.getY());

            currentCanvasController.redrawAllFigures();
        }
    }

        private Point getLastPoint() {
            return figure.getPoints().get(figure.getPoints().size() - 1);
        }

}
