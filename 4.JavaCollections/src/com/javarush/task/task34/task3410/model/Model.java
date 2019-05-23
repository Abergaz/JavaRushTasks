package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.io.File;
import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20; //Размер ячейки игрового поля Все игровые объекты будут занимать одну ячейку игрового поля. Именно этот размер будет участвовать в расчёте движения и столкновений объектов. Размер, который будет храниться внутри объекта, будет использоваться только при его отрисовке.
    private EventListener eventListener;
    private GameObjects gameObjects; //Оно будет хранить наши игровые объекты.
    private int currentLevel = 1; //Поле отвечающее за текущий уровень
    private LevelLoader levelLoader = new LevelLoader(Paths.get(new File(Model.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Model.class.getPackage().getName().replaceAll("[.]", "/")+"/../res/levels.txt").toURI()));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        this.gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        this.currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction)) {
            return;
        }
        if (checkBoxCollisionAndMoveIfAvaliable(direction)) {
            return;
        }

        switch (direction) {
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer();
        GameObject stoped = null;
        for (GameObject gameObject : gameObjects.getAll()) {
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject, direction)) {
                stoped = gameObject;
            }
        }

        if ((stoped == null)) {
            return false;
        }

        if (stoped instanceof Box) {
            Box stopedBox = (Box) stoped;
            if (checkWallCollision(stopedBox, direction)) {
                return true;
            }
            for (Box box : gameObjects.getBoxes()) {
                if (stopedBox.isCollision(box, direction)) {
                    return true;
                }
            }

            switch (direction) {
                case LEFT:
                    stopedBox.move(-FIELD_CELL_SIZE, 0);
                    break;
                case RIGHT:
                    stopedBox.move(FIELD_CELL_SIZE, 0);
                    break;
                case UP:
                    stopedBox.move(0, -FIELD_CELL_SIZE);
                    break;
                case DOWN:
                    stopedBox.move(0, FIELD_CELL_SIZE);
            }
        }
        return false;
    }

    public void checkCompletion() {
        boolean isLevelCompleted = true;

        for (Home home : gameObjects.getHomes()) {
            boolean chk = false;

            for (Box box : gameObjects.getBoxes()) {
                if ((box.getX() == home.getX()) && (box.getY() == home.getY()))
                    chk = true;
            }
            if (!chk) isLevelCompleted = false;
        }

        if (isLevelCompleted)
            eventListener.levelCompleted(currentLevel);
    }
}
