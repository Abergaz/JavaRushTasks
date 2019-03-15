package com.javarush.task.task05.task0531;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Совершенствуем функциональность
Текущая реализация: Программа вводит два числа с клавиатуры и выводит минимальное из них на экран.
Новая задача: Программа вводит пять чисел с клавиатуры и выводит минимальное из них на экран.


Требования:
1. Программа должна считывать числа с клавиатуры.
2. В классе должен быть метод public static void main.
3. В классе должен быть метод public static min, принимающий 5 параметров типа int.
4. Метод min должен возвращать минимум из 5 переданных чисел. Если минимальных чисел несколько - вернуть любое из них.
5. Программа должна выводить строку, которая начинается на "Minimum = ".
6. Программа должна выводить строку, которая заканчивается минимальным из 5 введенных чисел.
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a[] = new int[5];
        for (int i=0; i<a.length;i++) {
            a[i] = Integer.parseInt(reader.readLine());
        }

        int minimum = min(a[0],a[1],a[2],a[3],a[4]);

        System.out.println("Minimum = " + minimum);
    }


    public static int min(int aa, int b, int c, int d, int e ) {
        int a[] = new int[5];
        a[0]=aa;
        a[1]=b;
        a[2]=c;
        a[3]=d;
        a[4]=e;
        int tmp;
        tmp=a[0];
        for(int i=0; i<a.length;i++){
            tmp=(a[i]<tmp)? a[i] : tmp;
        }
        return tmp;
    }
}

