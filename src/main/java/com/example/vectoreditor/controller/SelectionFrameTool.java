package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.drawers.RectangleDrawer;
import com.example.vectoreditor.model.figures.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class SelectionFrameTool extends Tool implements ITool {

    Rectangle frame;
    RectangleDrawer drawer;
    public SelectionFrameTool(MainController mainController) {
        super(mainController);
        frame = new Rectangle(mainController.getPropertiesBoxController().getDecorationProperties());
    }

    @Override
    public void mousePressed(MouseEvent event) {
        frame.getLeftTop().setX(event.getX());
        frame.getLeftTop().setY(event.getY());

        frame.getRightTop().setY(event.getY());

        frame.getLeftBot().setX(event.getX());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        mainController.getCurrentCanvasController().redrawAllFigures();
        frame.getRightTop().setX(event.getX());
        frame.getRightBot().setX(event.getX());
        frame.getRightBot().setY(event.getY());
        frame.getLeftBot().setY(event.getY());
        GraphicsContext graphicsContext = mainController.getCurrentCanvasController().getDrawCanvas().getGraphicsContext2D();
        graphicsContext.setStroke(Color.LIGHTGRAY);
        graphicsContext.setLineDashes(0.3);
        graphicsContext.setLineCap(StrokeLineCap.BUTT);


        frame.draw(graphicsContext);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        mainController.setCurrentTool(new SelectTool(mainController));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }
}
