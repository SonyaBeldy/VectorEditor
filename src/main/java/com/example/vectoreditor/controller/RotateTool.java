package com.example.vectoreditor.controller;

import javafx.scene.input.MouseEvent;

public class RotateTool extends SelectTool  implements ITool{
    public RotateTool(CanvasController canvasController) {
        super(canvasController);
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {

        System.out.println();
    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }
}
