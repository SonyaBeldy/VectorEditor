package com.example.vectoreditor.controller.figureTool;

import com.example.vectoreditor.controller.*;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.figure.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Optional;

public class RectangleTool extends Tool implements ITool {

    private FigureItemController drawingFigure;

    private Rectangle rectangle;
    public RectangleTool(MainController mainController) {
        super(mainController);
        rectangle = new Rectangle(Color.BLACK);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        rectangle = new Rectangle(mainController.getCurrentCanvasController().getStrokeColor());
        for (int i = 0; i < 4; i++) {
            rectangle.addPoint(new Point(event.getX(), event.getY()));
        }
        FigureItemController figureController = mainController.getCurrentCanvasController().getCurrentLayer().addFigure(rectangle);
        drawingFigure = figureController;
        //mainController.getCurrentCanvasController().setCurrentFigureController(Optional.of(figureController));
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        rectangle.getPoints().get(1).setX(event.getX());

        rectangle.getPoints().get(2).setX(event.getX());
        rectangle.getPoints().get(2).setY(event.getY());

        rectangle.getPoints().get(3).setY(event.getY());

        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        rectangle.calcBoardsPoints();
        rectangle.calcCenter();
        mainController.getCurrentCanvasController().setCurrentFigureController(Optional.of(drawingFigure));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }
}
