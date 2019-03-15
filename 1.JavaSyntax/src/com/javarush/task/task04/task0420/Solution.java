package com.javarush.task.task04.task0420;

/* 
Сортировка трех чисел
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
        int x;

        if (a>b) {
            if (a > c) {
                System.out.println(a);
                if (b > c) {
                    System.out.println(b);
                    System.out.println(c);
                } else {
                    System.out.println(c);
                    System.out.println(b);
                }
            } else {
                System.out.println(c);
                if (a > b) {
                    System.out.println(a);
                    System.out.println(b);
                } else {
                    System.out.println(b);
                    System.out.println(a);
                }
            }
        }
        else
            {
            if (b>c){
                System.out.println(b);
                if (a>c){
                    System.out.println(a);
                    System.out.println(c);
                }else{
                    System.out.println(c);
                    System.out.println(a);
                }
            }else{
                System.out.println(c);
                if (a>b){
                    System.out.println(a);
                    System.out.println(b);
                }else{
                    System.out.println(b);
                    System.out.println(a);
                }
            }
        }

    }
}
