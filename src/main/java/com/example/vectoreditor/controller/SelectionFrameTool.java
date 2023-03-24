package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.drawer.RectangleDrawer;
import com.example.vectoreditor.model.figure.Polygon;
import com.example.vectoreditor.model.figure.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class SelectionFrameTool extends Tool implements ITool {

    Rectangle frame;
    RectangleDrawer drawer;
    public SelectionFrameTool(MainController mainController) {
        super(mainController);

    }

    @Override
    public void mousePressed(MouseEvent event) {
        frame = new Rectangle(Color.GRAY, new Point(event.getX(), event.getY()));
        drawer = new RectangleDrawer(frame.getPoints());
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

        graphicsContext.setLineCap(StrokeLineCap.BUTT);

        drawer.draw(mainController.getCurrentCanvasController().getDrawCanvas().getGraphicsContext2D(), Color.GRAY, 6);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        mainController.setCurrentTool(new SelectTool(mainController));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    public void setLineDashes(double dash){
       // mainController
    }
}
