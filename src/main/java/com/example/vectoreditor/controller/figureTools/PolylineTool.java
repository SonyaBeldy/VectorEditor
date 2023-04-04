package com.example.vectoreditor.controller.figureTools;

import com.example.vectoreditor.controller.*;
import com.example.vectoreditor.model.*;
import com.example.vectoreditor.model.figures.Polyline;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class PolylineTool extends Tool implements ITool {

    private boolean isDrawing;
    private FigureItemController drawingFigure;

    public PolylineTool(MainController mainController) {
        super(mainController);
        isDrawing = false;
    }

    protected void createFigure() {
        figure = new Polyline(mainController.getPropertiesBoxController().getDecorationProperties());
    }
    @Override
    public void mousePressed(MouseEvent event) {
        CanvasViewController currentCanvasController = mainController.getCurrentCanvasController();
        if (event.isPrimaryButtonDown()) {
            if (!isDrawing) {
                createFigure();
                isDrawing = true;
                figure.addPoint(new Point(event.getX(), event.getY()));

                figure.calcCenter();
                FigureItemController figureController = currentCanvasController.getCurrentLayer().addFigure(figure);
                currentCanvasController.setCurrentFigureController(Optional.empty());
                drawingFigure = figureController;
            }
            figure.addPoint(new Point(event.getX(), event.getY()));
        }

        if (event.isSecondaryButtonDown()) {
            isDrawing = false;
            if (figure.getPoints().size() > 2){
                figure.getPoints().remove(figure.getPoints().size() - 1);

                figure.calcCenter();
                currentCanvasController.setCurrentFigureController(Optional.of(drawingFigure));
                mainController.getPropertiesBoxController().update();
                currentCanvasController.redrawAllFigures();
            } else {
                currentCanvasController.getCurrentLayer().removeFigure(currentCanvasController.getCurrentLayer().getFiguresCount() - 1);
                currentCanvasController.setCurrentFigureController(Optional.empty());
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

//        figure.calcBoardsPoints();
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
