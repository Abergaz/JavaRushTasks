package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.io.File;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        String s = "SSS";
        Object o = (Object) s;
        Integer i = (Integer) o;

    }

    public void methodThrowsNullPointerException() {
        File file=null;
        file.length();

    }

    public static void main(String[] args) {

    }
}
