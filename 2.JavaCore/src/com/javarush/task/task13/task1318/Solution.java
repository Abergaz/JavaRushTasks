package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
1. Считать с консоли имя файла.
2. Вывести в консоль(на экран) содержимое файла.
3. Не забыть освободить ресурсы. Закрыть поток чтения с файла и поток ввода с клавиатуры.


Требования:
1. Программа должна считывать c консоли имя файла.
2. Программа должна выводить на экран содержимое файла.
3. Поток чтения из файла (FileInputStream) должен быть закрыт.
4. BufferedReader также должен быть закрыт.
*/

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(System.in));
        String fileName = null;
        try {
            fileName = bufferedReader.readLine();
            System.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream fileInputStream=null;
        try {
             fileInputStream = new FileInputStream(fileName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        try {
            while (bufferedInputStream.available()>0){
                System.out.print((char)bufferedInputStream.read());
            }
            fileInputStream.close();
            bufferedReader.close();
            bufferedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}