package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.figure.Figure;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

public class Tool {
    protected final ScrollPaneController currentCanvasController;
//    protected final Canvas drawCanvas;
    public Figure figure;


    public Tool(ScrollPaneController currentCanvasController) {
        this.currentCanvasController = currentCanvasController;
//        drawCanvas = canvasController.getDrawCanvas();
    }
}
