package com.javarush.task.task22.task2213;

public class Tetris {
    public static Tetris game;
    private Field field;
    private  Figure figure;

    public void setField(Field field) {
        this.field = field;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public void run(){

    }

    public void step(){

    }

    public static void main(String[] args) {
        Tetris.game = new Tetris();
        Tetris.game.run();
    }
}
