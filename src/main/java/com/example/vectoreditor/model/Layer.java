package com.example.vectoreditor.model;

import com.example.vectoreditor.model.figures.Figure;

import java.util.ArrayList;

public class Layer {

    private String name;
    private final ArrayList<Figure> figures;


    public Layer(String name) {
        this.name = name;
        figures = new ArrayList<>();
    }


    public void remove(int ind){
        figures.remove(ind);
    }
    public int getFiguresCount() {
        return figures.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    public Figure getFigure(int ind) {
        return figures.get(ind);
    }


}
