package com.example.vectoreditor.controller;


import javafx.scene.Node;

public class NodeController<T> {
    Node node;
    T controller;
    public NodeController(Node node, T controller) {
        this.node = node;
        this.controller = controller;
    }
}