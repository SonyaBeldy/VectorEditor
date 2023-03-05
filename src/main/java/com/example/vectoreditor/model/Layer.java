package com.example.vectoreditor.model;

import com.example.vectoreditor.model.figure.Figure;

import java.util.ArrayList;

public class Layer {

    private String name = "Layer";
    private ArrayList<Figure> figures;

    public Layer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Figure> getFigures() {
        return figures;
    }

}
