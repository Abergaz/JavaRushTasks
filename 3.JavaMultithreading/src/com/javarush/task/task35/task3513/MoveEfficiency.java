package com.javarush.task.task35.task3513;


/**
 * описывает эффективность хода
 */
public class MoveEfficiency implements Comparable<MoveEfficiency> {
    //количесиво пустыъ клеток после хода
    private int numberOfEmptyTiles;
    //счет после хода
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    /**
     * В методе compareTo первым делом сравни количество пустых плиток (numberOfEmptyTiles),
     * потом счет (score), если количество пустых плиток равное.
     * Если и счет окажется равным, будем считать эффективность ходов равной и вернем ноль.     *
     */
    @Override
    public int compareTo(MoveEfficiency that) {
        if (this == that) return 0;

        if (this.numberOfEmptyTiles > that.numberOfEmptyTiles) return 1;
        if (this.numberOfEmptyTiles < that.numberOfEmptyTiles) return -1;

        if (this.numberOfEmptyTiles == that.numberOfEmptyTiles) {
            if (this.score > that.score) return 1;
            if (this.score < that.score) return -1;
        }
        return 0;
    }
}
