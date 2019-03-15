package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int count=0;
        int summ=0;
        int i = Integer.parseInt(bufferedReader.readLine());
        while (i!=-1){
            count++;
            summ+=i;
            i = Integer.parseInt(bufferedReader.readLine());
        }
        if (count>0) System.out.println((double)summ/count);
        else System.out.println(0);

    }
}

