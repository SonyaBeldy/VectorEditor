package com.example.vectoreditor.controller.figureTool;

import com.example.vectoreditor.controller.*;
import com.example.vectoreditor.model.*;
import com.example.vectoreditor.model.figure.Polyline;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Optional;

public class PolylineTool extends Tool implements ITool {

    private boolean isDrawing;

    public PolylineTool(MainController mainController) {
        super(mainController);
        isDrawing = false;
        figure = new Polyline(Color.BLACK);

    }

    public void createFigure() {
        figure = new Polyline(mainController.getCurrentCanvasController().getStrokeColor());
    }
    @Override
    public void mousePressed(MouseEvent event) {
        CanvasViewController currentCanvasController = mainController.getCurrentCanvasController();
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
                currentCanvasController.getCurrentLayer().removeFigure(currentCanvasController.getCurrentLayer().getFiguresCount() - 1);
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

        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (!isDrawing) {
            return;
        }
        getLastPoint().setX(event.getX());
        getLastPoint().setY(event.getY());

        figure.calcBoardsPoints();
        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (isDrawing) {
            getLastPoint().setX(event.getX());
            getLastPoint().setY(event.getY());

            mainController.getCurrentCanvasController().redrawAllFigures();
        }
    }

        private Point getLastPoint() {
            return figure.getPoints().get(figure.getPoints().size() - 1);
        }

}
