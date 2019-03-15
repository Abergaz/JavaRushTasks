package com.javarush.task.task06.task0622;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Числа по возрастанию
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //напишите тут ваш код
        int[] a = new int[5];
        int tmp;
        a[0] = Integer.parseInt(reader.readLine());
        for(int i=1; i<5; i++){
            tmp = Integer.parseInt(reader.readLine());
            if (a[i-1]<tmp){
                a[i] = tmp;
            }
            else{
                a[i]=a[i-1];
                a[i-1]=tmp;
            }
        }
        boolean b = true;
        while (b){
            b=false;
            for (int i=0;i<4;i++){
                if (a[i]>a[i+1]){
                    tmp=a[i];
                    a[i]=a[i+1];
                    a[i+1]=tmp;
                    b=true;
                }
            }
        }
        for (int x : a){
            System.out.println(x);
        }
    }
}
