package com.javarush.task.task07.task0720;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/* 
Перестановочка подоспела
Ввести с клавиатуры 2 числа N и M.
Ввести N строк и заполнить ими список.
Переставить M первых строк в конец списка.
Вывести список на экран, каждое значение с новой строки.

Примечание: запрещено создавать больше одного списка.


Требования:
1. Объяви переменную типа список строк и сразу проинициализируй ee.
2. Считай c клавиатуры числа N и M, считай N строк и добавь их в список.
3. Переставить M первых строк в конец списка.
4. Выведи список на экран, каждое значение с новой строки.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //напишите тут ваш код
        ArrayList<String> arrayList = new ArrayList<String>();
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            arrayList.add(reader.readLine());
        }

        for (int i = 0; i < m; i++) {
            arrayList.add(arrayList.get(i));
        }

        Iterator<String> iterator = arrayList.iterator();
        for (int i = 0; i < m; i++){
            iterator.next();
            iterator.remove();
        }
        for (String s : arrayList){
            System.out.println(s);
        }
    }

}

