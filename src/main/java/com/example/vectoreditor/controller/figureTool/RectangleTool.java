package com.example.vectoreditor.controller.figureTool;

import com.example.vectoreditor.controller.*;
import com.example.vectoreditor.model.Frame2;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.figure.Rectangle;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class RectangleTool extends Tool implements ITool {

    private FigureItemController drawingFigure;

    private Rectangle rectangle;
    public RectangleTool(MainController mainController) {
        super(mainController);
//        rectangle = new Rectangle(mainController.getPropertiesBoxController().getDecorationProperties());
    }

    @Override
    public void mousePressed(MouseEvent event) {
        rectangle = new Rectangle(mainController.getPropertiesBoxController().getDecorationProperties());

        rectangle.getLeftTop().setX(event.getX());
        rectangle.getLeftTop().setY(event.getY());

        rectangle.getRightTop().setY(event.getY());

        rectangle.getLeftBot().setX(event.getX());
        drawingFigure = mainController.getCurrentCanvasController().getCurrentLayer().addFigure(rectangle);
        //mainController.getCurrentCanvasController().setCurrentFigureController(Optional.of(figureController));
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        rectangle.getRightTop().setX(event.getX());

        rectangle.getRightBot().setX(event.getX());
        rectangle.getRightBot().setY(event.getY());

        rectangle.getLeftBot().setY(event.getY());

        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
//        rectangle.calcBoardsPoints();
        rectangle.calcCenter();
        mainController.getCurrentCanvasController().setCurrentFigureController(Optional.of(drawingFigure));
        mainController.getPropertiesBoxController().update();
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }
}
