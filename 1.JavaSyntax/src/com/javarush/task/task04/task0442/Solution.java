package com.javarush.task.task04.task0442;


/* 
Суммирование
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int summ=0;
        int i=0;
        while (i!=-1){
            i = Integer.parseInt(bufferedReader.readLine());
            summ+=i;
        }
        System.out.println(summ);
    }
}
