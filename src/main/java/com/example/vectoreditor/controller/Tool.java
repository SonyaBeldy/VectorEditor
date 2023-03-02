package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.figure.Figure;
import javafx.scene.canvas.Canvas;

public class Tool {
    protected final CanvasController canvasController;
    protected final Canvas drawCanvas;
    public Figure figure;


    public Tool(CanvasController canvasController) {
        this.canvasController = canvasController;
        drawCanvas = canvasController.getDrawCanvas();
    }
}
