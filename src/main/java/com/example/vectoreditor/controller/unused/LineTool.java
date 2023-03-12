package com.example.vectoreditor.controller.unused;

import com.example.vectoreditor.controller.CanvasController;
import com.example.vectoreditor.controller.ITool;
import com.example.vectoreditor.controller.ScrollPaneController;
import com.example.vectoreditor.controller.Tool;
import javafx.scene.input.MouseEvent;

public class LineTool extends Tool implements ITool {

    public LineTool(ScrollPaneController currentCanvasController){
        super(currentCanvasController);
    }
    @Override
    public void mousePressed(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        //currentFigure = new Line(x,y, x, y, canvasController.getStrokeColor());
        //currentFigure.draw(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        //currentFigure.setEndPoint(x, y);
        currentCanvasController.redrawAllFigures();

        //currentFigure.draw(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        currentCanvasController.redrawAllFigures();
        //currentFigure.setEndPoint(x, y);
        //currentFigure.draw(drawCanvas.getGraphicsContext2D());

        //currentFigure.calcBoardsPoints();
        //currentFigure.drawBorders(drawCanvas.getGraphicsContext2D());

        //canvasController.addFigure(currentFigure);
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

}
