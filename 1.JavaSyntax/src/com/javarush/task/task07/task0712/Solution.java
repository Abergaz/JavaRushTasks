package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
1. Создай список строк.
2. Добавь в него 10 строчек с клавиатуры.
3. Узнай, какая строка в списке встретится раньше: самая короткая или самая длинная.
Если таких строк несколько, то должны быть учтены самые первые из них.
4. Выведи на экран строку из п.3. Должна быть выведена одна строка.


Требования:
1. Объяви переменную типа список строк и сразу проинициализируй ee.
2. Программа должна считывать 10 строк с клавиатуры и добавлять их в список.
3. Программа должна выводить на экран самую короткую строку, если она была раньше самой длинной.
4. Программа должна выводить на экран самую длинную строку, если она была раньше самой короткой.
5. Должна быть выведена только одна строка.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> arrayList = new ArrayList<String>();
        int min = Integer.MAX_VALUE;
        int i_min=0;
        int max = Integer.MIN_VALUE;
        int i_max=0;
        for (int i=0; i<10; i++){
            arrayList.add(bufferedReader.readLine());
        }
        for (int i=0; i<10; i++){
          if (min>arrayList.get(i).length()){
              min=arrayList.get(i).length();
              i_min=i;
          }
          if (max<arrayList.get(i).length()){
              max=arrayList.get(i).length();
              i_max=i;
          }
        }
        if (i_min<i_max) System.out.println(arrayList.get(i_min));
        else System.out.println(arrayList.get(i_max));

    }
}
