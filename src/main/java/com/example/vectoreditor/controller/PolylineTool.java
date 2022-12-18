package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Line;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.Polyline;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PolylineTool extends Tool implements ITool{

    private boolean isDrawing;
    private Polyline polyline;

    private Line line;

    public PolylineTool(CanvasController canvasController) {
        super(canvasController);
        isDrawing = false;
        line = new Line(0,0,0,0, Color.BLACK);
        polyline = new Polyline(Color.BLACK);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            double x = event.getX();
            double y = event.getY();

            if (isDrawing) {
                line = new Line(line.getEndPoint().getX(), line.getEndPoint().getY(), x, y, canvasController.getFillColor());
                line.draw(drawCanvas.getGraphicsContext2D());

                polyline.draw(drawCanvas.getGraphicsContext2D());

            } else {
                isDrawing = true;
                polyline = new Polyline(Color.BLACK);
                line = new Line(x, y, x, y, canvasController.getFillColor());
                line.draw(drawCanvas.getGraphicsContext2D());

            }
            currentFigure = line;
            polyline.addLine(line);

        }

        if (event.isSecondaryButtonDown()) {
            isDrawing = false;
            if (!polyline.getLines().isEmpty()){
                //polyline.getLines().remove(polyline.getLines().size() - 1);
                currentFigure = polyline;
                canvasController.addFigure(currentFigure);
                canvasController.redrawAllFigures();
            }

        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (!isDrawing) {
            return;
        }
        double x = event.getX();
        double y = event.getY();

        line.setEndPoint(x, y);
        canvasController.redrawAllFigures();
        polyline.draw(drawCanvas.getGraphicsContext2D());

        line.draw(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (!isDrawing) {
            return;
        }
        double x = event.getX();
        double y = event.getY();
        canvasController.redrawAllFigures();
        line.setEndPoint(x, y);
        line.draw(drawCanvas.getGraphicsContext2D());
        polyline.draw(drawCanvas.getGraphicsContext2D());


    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (isDrawing) {
            double x = event.getX();
            double y = event.getY();

            line.setEndPoint(x, y);
            canvasController.redrawAllFigures();
            polyline.draw(drawCanvas.getGraphicsContext2D());

            line.draw(drawCanvas.getGraphicsContext2D());
        }
    }
}
