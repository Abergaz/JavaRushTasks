package com.javarush.task.task03.task0322;


/* 
Большая и чистая
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        System.out.println(bufferedReader.readLine() + " + " + bufferedReader.readLine() + " + " + bufferedReader.readLine() + " = Чистая любовь, да-да!");
    }
}