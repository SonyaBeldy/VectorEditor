package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import javafx.scene.canvas.Canvas;

public class Tool {
    protected final CanvasController canvasController;
    protected final Canvas drawCanvas;
    protected Figure currentFigure;


    public Tool(CanvasController canvasController) {
        this.canvasController = canvasController;
        drawCanvas = canvasController.getDrawCanvas();
        currentFigure = canvasController.getCurrentFigure();
    }
}
