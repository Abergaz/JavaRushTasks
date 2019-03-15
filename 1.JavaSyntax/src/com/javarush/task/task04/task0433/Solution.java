package com.javarush.task.task04.task0433;


/* 
Гадание на долларовый счет
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int i = 10;
        int ii;
        while (i>0){
            ii=10;
            while (ii>0) {
                System.out.print("S");
                ii--;
            }
            System.out.println();
            i--;
        }
    }
}
