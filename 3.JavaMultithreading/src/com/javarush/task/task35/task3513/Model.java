package com.javarush.task.task35.task3513;

import java.util.*;

/**
 * будет содержать игровую логику и хранить игровое поле
 * и будет ответственен за все манипуляции производимые с игровым полем
 */
public class Model {
    /**
     * размер игрового поля
     */
    private static final int FIELD_WIDTH = 4;
    /**
     * игровое поле
     */
    private Tile[][] gameTiles;
    /**
     * текущий счет
     */
    int score;
    /**
     * максимальный вес плитки на игровом поле
     */
    int maxTile;

    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    /**
     * будет сохранять текущее
     * игровое состояние и счет в стеки с помощью метода push и устанавливать флаг isSaveNeeded равным false.
     */
    private void saveState(Tile[][] tiles) {
        Tile[][] saveTiles = new Tile[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                saveTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(saveTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    /**
     * будет устанавливать текущее игровое состояние равным последнему находящемуся в стеках с помощью метода pop
     */
    public void rollback() {
        if (!previousScores.isEmpty() && !previousStates.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }


    /**
     * @return true в случае, если в текущей позиции возможно сделать ход так, чтобы состояние игрового поля изменилось. Иначе - false.
     * ход можно сделать если есть пустые(0) ячейки, или соседние с права или снизу ячейки одинаковые
     */
    public boolean canMove() {
        for (int i = 0; i < gameTiles.length - 1; i++) {
            for (int j = 0; j < gameTiles[i].length - 1; j++) {
                if (gameTiles[i][j].value == 0 || gameTiles[i][j].value == gameTiles[i][j + 1].value || gameTiles[i][j].value == gameTiles[i + 1][j].value)
                    return true;
            }
        }
        return false;
    }

    /**
     * @return должен возвращать список пустых плиток в массиве gameTiles
     */
    private List<Tile> getEmptyTiles() {
        ArrayList<Tile> retList = new ArrayList<>();
        int size = gameTiles.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile tile = this.gameTiles[i][j];
                if (tile.isEmpty()) {
                    retList.add(tile);
                }
            }
        }
        return retList;
    }

    /**
     * должен изменять значение случайной пустой плитки в массиве gameTiles на 2 или 4 с вероятностью 0.9 и 0.1 соответственно
     */
    private void addTile() {
        ArrayList<Tile> emptyTiles = new ArrayList<>(getEmptyTiles());
        if (emptyTiles.size() > 0) {
            /** случайный объект из списка = размерСписка * случайноеЧислоОтНуляДоЕдиницы */
            int rnd = (int) (getEmptyTiles().size() * Math.random());
            /** вес новой плитки = (Math.random() < 0.9 ? 2 : 4) */
            int value = (Math.random() < 0.9 ? 2 : 4);
            /** устанавливаем случайный вес в случайную плитку*/
            emptyTiles.get(rnd).value = value;
        }
    }

    /**
     * должен заполнять массив gameTiles новыми плитками и менять значение двух из них с помощью двух вызовов метода addTile.
     */
    protected void resetGameTiles() {
        int size = Model.FIELD_WIDTH;
        this.gameTiles = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
        score = 0;
        maxTile = 0;
    }

    /**
     * Сжатие плиток, таким образом, чтобы все пустые плитки были справа, т.е. ряд {4, 2, 0, 4} становится рядом {4, 2, 4, 0}
     */
    private boolean compressTiles(Tile[] tiles) {
        boolean ret = false;
        while (true) {
            boolean hasChange = false;
            for (int i = 0; i < tiles.length - 1; i++) {
                if (tiles[i].value == 0 && tiles[i + 1].value != 0) {
                    tiles[i].value = tiles[i + 1].value;
                    tiles[i + 1].value = 0;
                    hasChange = true;
                    ret = true;
                }
            }
            if (!hasChange) break;
        }
        return ret;
    }

    /**
     * Слияние плиток одного номинала, т.е. ряд {4, 4, 2, 0} становится рядом {8, 2, 0, 0}.
     */
    private boolean mergeTiles(Tile[] tiles) {
        boolean ret = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value != 0 && tiles[i].value == tiles[i + 1].value) {
                tiles[i].value = tiles[i].value * 2;
                tiles[i + 1].value = 0;
                if (maxTile < tiles[i].value) maxTile = tiles[i].value;
                score += tiles[i].value;
                ret = true;
            }
        }
        compressTiles(tiles);
        return ret;
    }


    /**
     * Делает сдвиг в лево
     */
    void left() {
        if (isSaveNeeded) saveState(gameTiles);
        if (supportLeft()) isSaveNeeded = true;
    }

    /**
     * вспомпоготальный метод, который  делает сдвиг в лево
     *
     * @return
     */
    private boolean supportLeft() {
        boolean flag = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) flag = true;
        }
        if (flag) {
            addTile();
            return true;
        }
        return false;
    }

    /**
     * Делает сдвиг вниз, для этого поворачивает матрицу 2 раза в право на 90, затем вызывается сдвиг в лево и 2 раза повот на 90 в право
     */
    void right() {
        if (isSaveNeeded) saveState(gameTiles);
        turnToRight(gameTiles);
        turnToRight(gameTiles);
        if (supportLeft()) isSaveNeeded = true;
        turnToRight(gameTiles);
        turnToRight(gameTiles);
    }

    /**
     * Делает сдвиг вверх, для этого поворачивает матрицу 3 раза в право на 90, затем вызывается сдвиг в лево и 1 раз повот на 90 в право
     */
    void up() {
        if (isSaveNeeded) saveState(gameTiles);
        turnToRight(gameTiles);
        turnToRight(gameTiles);
        turnToRight(gameTiles);
        if (supportLeft()) isSaveNeeded = true;
        turnToRight(gameTiles);
    }

    /**
     * Делает сдвиг вниз, для этого поворачивает матрицу вправо на 90, затем вызывается сдвиг в лево и 3 раза повот на 90 в право
     */
    void down() {
        if (isSaveNeeded) saveState(gameTiles);
        turnToRight(gameTiles);
        if (supportLeft()) isSaveNeeded = true;
        turnToRight(gameTiles);
        turnToRight(gameTiles);
        turnToRight(gameTiles);
    }

    /**
     * Поворот матрицы на  90 в право(по часовой стрелке)
     */
    private void turnToRight(Tile[][] array) {
        Tile[][] resultArray = new Tile[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                resultArray[j][array.length - i - 1] = array[i][j];
            }
        }
        gameTiles = resultArray;
    }

    /**
     * будет вызывать один из методов движения случайным образом
     */
    void randomMove() {
        /**
         * Это число будет содержать целое псевдослучайное число в диапазоне [0..3]
         */
        int n = ((int) (Math.random() * 100)) % 4;

        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    /**
     * будет возвращать true, в случае, если вес плиток в массиве gameTiles отличается от веса плиток в верхнем массиве стека previousStates.
     * Обрати внимание на то, что мы не должны удалять из стека верхний элемент, используй метод peek.
     */
    private boolean hasBoardChanged() {
        //берем последний сохраненый ход и сравниваем
        Tile[][] tmp = previousStates.peek();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                if (gameTiles[i][j].value != tmp[i][j].value) return true;
            }
        }
        return false;
    }

    /**
     * принимает один параметр типа move, и возвращает объект типа MoveEfficiency описывающий эффективность переданного хода. Несколько советов:
     * а) не забудь вызывать метод rollback, чтобы восстановить корректное игровое состояние;
     * б) в случае, если ход не меняет состояние игрового поля, количество пустых плиток и счет у объекта MoveEfficiency сделай равными -1 и 0 соответственно;
     * в) выполнить ход можно вызвав метод move на объекте полученном в качестве параметра.
     */
    private MoveEfficiency getMoveEfficiency(Move move) {
        //делаем ход
        MoveEfficiency moveEfficiency;

        move.move();

        if (hasBoardChanged()) {
            rollback();
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);

        } else {
            rollback();
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }

        return moveEfficiency;
    }

    /**
     * будет выбирать лучший из возможных ходов и выполнять его.
     */
    public void autoMove(){
        /** для того, чтобы вверху очереди всегда был максимальный элемент и размером равным четырем. */
        PriorityQueue<MoveEfficiency> priorityQueue=new PriorityQueue<>(4, Collections.reverseOrder());

        /** Заполним PriorityQueue четырьмя объектами типа MoveEfficiency (по одному на каждый вариант хода). */
        priorityQueue.offer(getMoveEfficiency(this::left));
        priorityQueue.offer(getMoveEfficiency(this::right));
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.offer(getMoveEfficiency(this::down));
        /** Возьмем верхний элемент и выполним ход связанный с ним. */
        priorityQueue.peek().getMove().move();
    }
    public static void main(String[] args) {
        Model m = new Model();
        m.gameTiles = new Tile[][]{{new Tile(4), new Tile(4), new Tile(2), new Tile(0)},
                {new Tile(4), new Tile(2), new Tile(0), new Tile(4)},
                {new Tile(4), new Tile(4), new Tile(4), new Tile(0)},
                {new Tile(4), new Tile(4), new Tile(4), new Tile(4)}};
        m.compressTiles(m.gameTiles[1]);
        System.out.println("");
        m.mergeTiles(m.gameTiles[3]);
        System.out.println("score=" + m.score);
        System.out.println("maxTile=" + m.maxTile);
    }
}
