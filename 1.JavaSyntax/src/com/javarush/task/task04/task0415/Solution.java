package com.javarush.task.task04.task0415;

/* 
Правило треугольника
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //System.out.print("Введите первую сторону: ");
        int a = Integer.parseInt(bufferedReader.readLine());
        //System.out.print("Введите вторую сторону: ");
        int b = Integer.parseInt(bufferedReader.readLine());
        //System.out.print("Введите третью сторону: ");
        int c = Integer.parseInt(bufferedReader.readLine());

        if ((a+b)>c && (a+c)>b && (c+b)>a){
            System.out.println("Треугольник существует.");
        }
        else {
            System.out.println("Треугольник не существует." );
        }
    }
}

