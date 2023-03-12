package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.figure.Figure;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

public class Tool {
    protected final MainController mainController;
//    protected final Canvas drawCanvas;
    public Figure figure;


    public Tool(MainController mainController) {
        this.mainController = mainController;
//        drawCanvas = canvasController.getDrawCanvas();
    }
}
