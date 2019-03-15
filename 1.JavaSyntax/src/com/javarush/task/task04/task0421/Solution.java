package com.javarush.task.task04.task0421;

/* 
Настя или Настя?
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String a = bufferedReader.readLine();
        String b = bufferedReader.readLine();

        if (a.equals(b)) System.out.println("Имена идентичны");
        else if (a.length() == b.length()) System.out.println("Длины имен равны");

    }
}
