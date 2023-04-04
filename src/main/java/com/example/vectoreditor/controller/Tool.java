package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.figures.Figure;

public class Tool {
    protected final MainController mainController;
//    protected final Canvas drawCanvas;
    public Figure figure;


    public Tool(MainController mainController) {
        this.mainController = mainController;
//        drawCanvas = canvasController.getDrawCanvas();
    }
}
