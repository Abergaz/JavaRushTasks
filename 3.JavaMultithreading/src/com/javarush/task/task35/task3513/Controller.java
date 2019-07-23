package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * будет следить за нажатием клавиш во время игры.
 */
public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private static final int WINNING_TILE = 2048;

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    public void resetGame() {
        view.isGameLost = false;
        view.isGameWon = false;
        model.score = 0;
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Если была нажата клавиша ESC - вызови метод resetGame
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            resetGame();
        }
        //Если метод canMove модели возвращает false - установи флаг isGameLost в true.
        if (!model.canMove()) {
            view.isGameLost = true;
        }

        //Если оба флага isGameLost и isGameWon равны false - обработай варианты движения:
        if (!view.isGameLost && !view.isGameWon) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    model.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    model.right();
                    break;
                case KeyEvent.VK_UP:
                    model.up();
                    break;
                case KeyEvent.VK_DOWN:
                    model.down();
                    break;
            }
        }
        //Если поле maxTile у модели стало равно WINNING_TILE, установи флаг isGameWon в true
        if (model.maxTile == WINNING_TILE) {
            view.isGameWon = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {
            model.rollback();
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            model.randomMove();
        }

        /** После реализации метода autoMove добавим его вызов в метод keyPressed класса Controller по нажатию на клавишу A */
        if (e.getKeyCode() == KeyEvent.VK_A) {
            model.autoMove();
        }

        view.repaint();

    }

    public View getView() {
        return view;
    }
}
