package com.javarush.task.task14.task1419;

import java.io.*;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
Заполни список exceptions десятью(10) различными исключениями.
Первое исключение уже реализовано в методе initExceptions.


Требования:
1. Список exceptions должен содержать 10 элементов.
2. Все элементы списка exceptions должны быть исключениями(потомками класса Throwable).
3. Все элементы списка exceptions должны быть уникальны.
4. Метод initExceptions должен быть статическим.
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        //напишите тут ваш код

        exceptions.add(new EOFException());
        exceptions.add(new FileNotFoundException());
        exceptions.add(new MalformedURLException());
        exceptions.add(new UnknownHostException());
        exceptions.add(new CharConversionException());
        exceptions.add(new InterruptedException());
        exceptions.add(new ObjectStreamException(){});
        exceptions.add(new SyncFailedException(""));
        exceptions.add(new IOException());


    }
}
