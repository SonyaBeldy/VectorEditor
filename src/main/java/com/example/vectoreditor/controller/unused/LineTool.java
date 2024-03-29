package com.example.vectoreditor.controller.unused;

import com.example.vectoreditor.controller.*;
import javafx.scene.input.MouseEvent;

public class LineTool extends Tool implements ITool {

    public LineTool(MainController mainController){
        super(mainController);
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
        mainController.getCurrentCanvasController().redrawAllFigures();

        //currentFigure.draw(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        mainController.getCurrentCanvasController().redrawAllFigures();
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
