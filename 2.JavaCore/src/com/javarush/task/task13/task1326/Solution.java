package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.

Пример ввода:
5
8
-2
11
3
-5
2
10

Пример вывода:
-2
2
8
10


Требования:
1. Программа должна считывать данные с консоли.
2. Программа должна создавать FileInputStream для введенной с консоли строки.
3. Программа должна выводить данные на экран.
4. Программа должна вывести на экран все четные числа считанные из файла отсортированные по возрастанию.
5. Программа должна закрывать поток чтения из файла(FileInputStream).
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream=null;
        String fileName = null;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        String s;
        Integer i=0;
        try {
             fileName = bufferedReader.readLine();
             fileInputStream = new FileInputStream(fileName);
             bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
             while (true){
                 s=bufferedReader.readLine();
                 if(s==null) break;
                 //System.out.println(s);
                 i=Integer.parseInt(s);
                 if (i % 2 == 0) {
                     arrayList.add(i);
                 }

             }
             bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(arrayList);
        for (int ii:arrayList) {
            System.out.println(ii);
        }
    }
}
