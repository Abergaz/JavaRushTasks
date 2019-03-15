package com.javarush.task.task04.task0412;

/* 
Положительное и отрицательное число
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream=System.in;
        InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        int a = Integer.parseInt(bufferedReader.readLine());
        if (a>0) {
            System.out.println(a*2);
        }else if(a<0){
            System.out.println(a+1);
        }else{
            System.out.println(a);
        }
    }

}