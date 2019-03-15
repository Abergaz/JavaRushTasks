package com.javarush.task.task04.task0414;

/* 
Количество дней в году
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        int in=0;
        in=Integer.parseInt(bufferedReader.readLine());
        if (in % 400 == 0){
            System.out.println("количество дней в году: 366");
        }
        else if (in % 100 == 0){
            System.out.println("количество дней в году: 365");

        }
        else if (in % 4 == 0){
            System.out.println("количество дней в году: 366");
        }
        else{
            System.out.println("количество дней в году: 365");
        }
    }
}