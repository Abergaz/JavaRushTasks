package com.javarush.task.task05.task0522;

/* 
Максимум конструкторов
Изучи класс Circle. Напиши максимальное количество конструкторов с разными аргументами.

Подсказка:
не забудь про конструктор по умолчанию.


Требования:
1. У класса должно быть хотя бы три переменные.
2. У класса должен быть конструктор по умолчанию.
3. У класса должен быть хотя бы один конструктор.
4. У класса должно быть хотя бы два конструктора.
5. У класса должно быть хотя бы три конструктора.
6. У класса должно быть хотя бы четыре конструктора.
*/

import javax.crypto.Cipher;

public class Circle {
    public double x;
    public double y;
    public double radius;

    //напишите тут ваш код
    public Circle(){
      this.x = 0.0;
      this.y = 0.0;
      this.radius = 0.0;
    }
    public Circle(int x){
        this();
        this.x = x;
    }
    public Circle(int x, int y){
        this(x);
        this.y = y;
    }
    public Circle(int x, int y, int radius){
        this(x,y);
        this.radius = radius;
    }
    public Circle(Circle circle){
        this.x = circle.x;
        this.y = circle.y;
        this.radius = circle.radius;
    }

    public static void main(String[] args) {

    }
}