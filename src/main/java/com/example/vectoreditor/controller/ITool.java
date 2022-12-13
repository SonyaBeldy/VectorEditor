package com.example.vectoreditor.controller;

import javafx.scene.input.MouseEvent;

public interface ITool {
    void mousePressed(MouseEvent event);
    void mouseDragged(MouseEvent event);
    void mouseReleased(MouseEvent event);
}
