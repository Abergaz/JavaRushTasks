package com.javarush.task.task04.task0424;

/* 
Три числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(bufferedReader.readLine());
        int b = Integer.parseInt(bufferedReader.readLine());
        int c = Integer.parseInt(bufferedReader.readLine());
        if (a!=b && a!=c && b==c) System.out.println(1);
        if (a!=b && b!=c && a==c) System.out.println(2);
        if (a!=c && b!=c & a==b) System.out.println(3);
    }
}
