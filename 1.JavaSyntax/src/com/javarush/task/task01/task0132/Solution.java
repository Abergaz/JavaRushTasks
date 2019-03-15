package com.javarush.task.task01.task0132;

/* 
Сумма цифр трехзначного числа
*/

import javafx.stage.Screen;

public class Solution {
    public static void main(String[] args) {
        System.out.println(sumDigitsInNumber(546));
    }

    public static int sumDigitsInNumber(int number) {
        //напишите тут ваш код
        String s = String.valueOf(number);
        int summ=0;

        for( Character x:s.toCharArray()) {
            summ+=Integer.parseInt(x.toString());
        }
        return summ;
    }
}