package com.javarush.task.task03.task0320;


/* 
Скромность украшает программиста
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        System.out.println(bufferedReader.readLine() + " зарабатывает $5,000. Ха-ха-ха!");
    }
}
