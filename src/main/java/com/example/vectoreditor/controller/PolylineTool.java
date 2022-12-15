package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Line;
import com.example.vectoreditor.model.Polyline;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PolylineTool extends Tool implements ITool{

    public PolylineTool(CanvasController canvasController) {
        super(canvasController);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        currentFigure = new Polyline(x,y, x, y, Color.BLACK);
        currentFigure.draw(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }
}
