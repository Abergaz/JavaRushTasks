package com.javarush.task.task07.task0707;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Что за список такой?
1. Создай список строк.
2. Добавь в него 5 различных строк.
3. Выведи его размер на экран.
4. Используя цикл выведи его содержимое на экран, каждое значение с новой строки.


Требования:
1. Программа не должна ничего считывать с клавиатуры.
2. Объяви переменную типа ArrayList<String> (список строк) и сразу проинициализируй ee.
3. Программа должна добавить 5 любых строк в список.
4. Программа должна вывести размер списка на экран.
5. Программа должна вывести содержимое списка на экран, каждое значение с новой строки.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> arrayList = new ArrayList<String>();
        for(int i=0; i<5;i++){
            //arrayList.add(bufferedReader.readLine());
            arrayList.add("SDSADSADSADAD");
        }
        System.out.println(arrayList.size());
        for (String x : arrayList){
            System.out.println(x);
        }
    }
}
