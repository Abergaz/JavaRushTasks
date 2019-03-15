package com.javarush.task.task04.task0417;

/* 
Существует ли пара?
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        int a = Integer.parseInt(bufferedReader.readLine());
        int b = Integer.parseInt(bufferedReader.readLine());
        int c = Integer.parseInt(bufferedReader.readLine());

        if (a==b & a==c) {
            System.out.print(a + " " + b + " " + c);
        }else if (a==b){
            System.out.print(a + " " + b);
        }else if(a==c){
            System.out.print(a + " " + c);
        }else if(b==c){
            System.out.print(b + " " + c);
        }
    }
}