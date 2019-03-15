package com.javarush.task.task04.task0441;


/* 
Как-то средненько
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(bufferedReader.readLine());
        int b = Integer.parseInt(bufferedReader.readLine());
        int c = Integer.parseInt(bufferedReader.readLine());
        int d;
        boolean bool=true;

        while (bool){
            bool=false;
            if (a>b){
                d=b;
                b=a;
                a=d;
                bool=true;
            }
            if (b>c){
                d=c;
                c=b;
                b=d;
                bool=true;
            }
        }
        System.out.println(b);

    }
}
