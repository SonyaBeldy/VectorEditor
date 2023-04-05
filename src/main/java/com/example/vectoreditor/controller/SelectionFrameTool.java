package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.drawers.RectangleDrawer;
import com.example.vectoreditor.model.figures.FigureDecorationData;
import com.example.vectoreditor.model.figures.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

import java.util.Optional;

public class SelectionFrameTool extends Tool implements ITool {

    Rectangle frame;
    public SelectionFrameTool(MainController mainController) {
        super(mainController);
        FigureDecorationData decorationData = new FigureDecorationData(Optional.of(Color.GRAY), Optional.empty(), 1);
        decorationData.setLineDashes(4);
//        frame = new Rectangle(mainController.getPropertiesBoxController().getDecorationProperties());
        frame = new Rectangle(decorationData);
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
//        graphicsContext.setLineCap(StrokeLineCap.BUTT);

        frame.draw(graphicsContext);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        mainController.setCurrentTool(new SelectTool(mainController));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    private void selectFigures() {

    }
}
