package com.javarush.task.task22.task2213;

public class Figure {
    /*
Теперь создадим костяк класса Figure.
Этот класс будет описывать падающую фигурку.

Нам понадобятся ее координаты и форма.
За координаты будут отвечать две переменные x и y.
За форму - матрица. Двумерный массив 3×3, состоящий из единиц и нулей.
Единицей мы обозначаем что клетка есть, нулем - что она пустая.

Добавь в класс Figure два поля поля: x типа int, y типа int.
Еще добавь двумерный массив: matrix(матрица) типа int[][].
Там же добавь getter'ы для созданных переменных.
Добавь конструктор с тремя параметрами x, y, matrix.


Требования:
1. В классе Figure должно быть создано приватное поле x типа int.
2. В классе Figure должно быть создано приватное поле y типа int.
3. В классе Figure должно быть создано приватное поле matrix типа int[][](целочисленный двумерный массив).
4. В классе Figure должен быть создан корректный геттер для поля х.
5. В классе Figure должен быть создан корректный геттер для поля y.
6. В классе Figure должен быть создан корректный геттер для поля matrix.
7. В классе Figure должен быть создан корректно работающий public конструктор с тремя параметрами int, int и int[][] (x, y и matrix).

Также нам понадобятся методы для управления фигуркой.

Добавь в класс Figure методы:
left() - для движения фигурки влево.
right() - для движения фигурки вправо.
down() - для движения фигурки вниз.
up() - для движения фигурки вверх.
rotate() - для поворота фигурки вокруг главной диагонали.
downMaximum() - падение фигурки в низ до дна.
boolean isCurrentPositionAvailable() - проверка - может ли фигурка быть помещена в текущую позицию. Для теста захардкодь результат в true, пока мы не реализовали логику.
landed() - вызывается, когда фигурка достигла дна или уперлась в другую фигурку
Все ее занятые клетки теперь должны добавиться в Field.


Требования:
1. В классе Figure должен быть создан метод left без параметров.
2. В классе Figure должен быть создан метод right без параметров.
3. В классе Figure должен быть создан метод down без параметров.
4. В классе Figure должен быть создан метод up без параметров.
5. В классе Figure должен быть создан метод rotate без параметров.
6. В классе Figure должен быть создан метод downMaximum без параметров.
7. В классе Figure должен быть создан метод isCurrentPositionAvailable без параметров.
8. В классе Figure должен быть создан метод landed без параметров.
9. Метод isCurrentPositionAvailable должен возвращать true.
     */
    private int x;
    private int y;
    private int[][] matrix;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void left(){
        this.x=this.x-1;
        if (!isCurrentPositionAvailable()){
            this.x=this.x+1;
        }
    }

    public void right(){
        this.x=this.x+1;
        if (!isCurrentPositionAvailable()){
            this.x=this.x-1;
        }
    }

    public void down(){
        this.y=this.y+1;
        if (!isCurrentPositionAvailable()){
            this.y=this.y-1;
        }
    }

    public void up(){
        this.y=this.y-1;
        if (!isCurrentPositionAvailable()){
            this.y=this.y+1;
        }
    }

    public void rotate(){

    }

    public void downMaximum(){

    }

    public boolean isCurrentPositionAvailable(){
        return true;
    }

    public void landed(Field field){
        System.out.println(this.x +" " + this.y);
        System.exit(1);
    }


    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }
}
