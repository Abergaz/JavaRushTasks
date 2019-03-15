package com.javarush.task.task04.task0427;

/* 
Описываем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(bufferedReader.readLine());
         if (a>=1 && a<=999){
            if (a%2==0 && (int)a/10==0) System.out.println("четное однозначное число");
            if (a%2!=0 && (int)a/10==0) System.out.println("нечетное однозначное число");
            if (a%2==0 && (int)a/10!=0 && (int)a/100==0) System.out.println("четное двузначное число");
            if (a%2!=0 && (int)a/10!=0 && (int)a/100==0) System.out.println("нечетное двузначное число");
            if (a%2==0 && (int)a/100!=0) System.out.println("четное трехзначное число");
            if (a%2!=0 && (int)a/100!=0) System.out.println("нечетное трехзначное число");
        }

    }
}
