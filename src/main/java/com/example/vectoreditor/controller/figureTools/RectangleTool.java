package com.example.vectoreditor.controller.figureTools;

import com.example.vectoreditor.controller.*;
import com.example.vectoreditor.model.figures.Polyline;
import com.example.vectoreditor.model.figures.Rectangle;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class RectangleTool extends Tool implements ITool {

    private FigureItemController drawingFigure;

    private Rectangle rectangle;
    public RectangleTool(MainController mainController) {
        super(mainController);
    }

    protected void createFigure() {
        rectangle = new Rectangle(mainController.getPropertiesBoxController().getDecorationProperties());
    }
    @Override
    public void mousePressed(MouseEvent event) {
        createFigure();

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
        rectangle.calcCenter();
        mainController.getCurrentCanvasController().setCurrentFigureController(Optional.of(drawingFigure));
        mainController.getPropertiesBoxController().update();
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }
}
