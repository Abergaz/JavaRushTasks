package com.javarush.task.task08.task0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Номер месяца
Программа вводит с клавиатуры имя месяца и выводит его номер на экран в виде: "May is the 5 month".
Используйте коллекции.


Требования:
1. Программа должна считывать одно значение с клавиатуры.
2. Программа должна выводить текст на экран.
3. Программа должна использовать коллекции.
4. Программа должна считывать с клавиатуры имя месяца и выводить его номер на экран по заданному шаблону
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("January" , "1");
        hashMap.put("February" , "2");
        hashMap.put("March" , "3");
        hashMap.put("April" , "4");
        hashMap.put("May" , "5");
        hashMap.put("June" , "6");
        hashMap.put("July" , "7");
        hashMap.put("August" , "8");
        hashMap.put("September", "9");
        hashMap.put("October" , "10");
        hashMap.put("November" , "11");
        hashMap.put("December", "12");

        String month = bufferedReader.readLine();
        System.out.println(month + " is the " + hashMap.get(month) + " month");
    }
}
