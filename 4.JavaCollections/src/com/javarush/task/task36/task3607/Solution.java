package com.javarush.task.task36.task3607;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.DelayQueue;

/*
Найти класс по описанию
1. Реализует интерфейс Queue.
2. Используется при работе с трэдами.
3. Из этой очереди элементы могут быть взяты только тогда, когда они заэкспарятся, их время задержки истекло.
4. Головой очереди является элемент, который заэкспарился раньше всех.


Требования:
1. Метод getExpectedClass не должен использовать метод getDeclaredClasses.
2. Метод getExpectedClass должен вернуть правильный тип.
3. Метод main должен вызывать метод getExpectedClass.
4. Метод main должен вывести полученный класс на экран.
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        return DelayQueue.class;
    }
}
