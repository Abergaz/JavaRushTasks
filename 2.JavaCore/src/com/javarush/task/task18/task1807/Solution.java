package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
С консоли считать имя файла.
Посчитать в файле количество символов ',', количество вывести на консоль.
Закрыть потоки.

Подсказка:
нужно сравнивать с ascii-кодом символа ','.


Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль должно выводится число запятых в считанном файле.
4. Поток чтения из файла должен быть закрыт.
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream=null;
        int count=0;
        int read;
        int asci =(int)',';
        try {
            fileInputStream = new FileInputStream(bufferedReader.readLine());
            while (fileInputStream.available()>0){
                read = fileInputStream.read();
                if (read==asci){
                    count++;
                }
            }
            fileInputStream.close();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
