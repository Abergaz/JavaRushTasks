package com.javarush.task.task22.task2213;
/*
Теперь перейдем к классу Field.
Он будет отвечать за хранение данных о текущих занятых и свободных клетках на поле игры.
Добавь в класс Field два поля поля: width (ширина) типа int, height(высота) типа int.
Так же нам понадобится матрица - двумерный массив: matrix(матрица) типа int[][];
Там же добавь getter'ы для созданных переменных.
Добавь конструктор с двумя параметрами width и height. И не забудь про матрицу.

ВАЖНО!
Двумерный массив можно представить как массив массивов или как прямоугольную матрицу.
При этом первой координатой в массиве у нас будет номер строки, а второй - столбца.
Другими словами ячейка с координатами x, y - это matrix[y][x].


Требования:
1. В классе Field должно быть создано приватное поле width типа int.
2. В классе Field должно быть создано приватное поле height типа int.
3. В классе Field должно быть создано приватное поле matrix типа int[][](целочисленный двумерный массив).
4. В классе Field должен быть создан корректный геттер для поля height.
5. В классе Field должен быть создан корректный геттер для поля width.
6. В классе Field должен быть создан корректный геттер для поля matrix.
7. В классе Field должен быть создан корректно работающий конструктор с параметрами int, int. Поле matrix должно быть инициализировано новым массивом размерностью [y][x] (height и width).

Нам понадобится еще 4 метода в классе Field:
а) print() - объект будет отрисовывать на экран свое текущее состояние;
б) removeFullLines() - будет удалять из матрицы полностью заполненные строки и сдвигать вышележащие строки вниз;
в) Integer getValue(int x, int y) - возвращает значение которое находится в матрице с координатами x и y;
г) void setValue(int x, int y, int value) - устанавливает переданное значение в ячейку массива (матрицы) с координатами x, y.


Требования:
1. В классе Field должен быть создан метод print без параметров.
2. В классе Field должен быть создан метод removeFullLines без параметров.
3. В классе Field должен быть создан метод getValue с двумя параметрами типа int.
4. В классе Field должен быть создан метод setValue c тремя параметрами типа int.
5. Метод print не должен возвращать значение.
6. Метод removeFullLines не должен возвращать значение.
7. Метод getValue должен возвращать значение типа Integer.
8. Метод setValue не должен возвращать значение.
 */
public class Field {
    private int width;
    private int height;
    private int[][] matrix;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void print(){};

    public void removeFullLines(){};

    public Integer getValue(int x, int y){
        return this.matrix[x][y];
    }

    public void setValue(int x, int y, int value){
        this.matrix[x][y]=value;
    }


    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new int[height][width];
    }
}
