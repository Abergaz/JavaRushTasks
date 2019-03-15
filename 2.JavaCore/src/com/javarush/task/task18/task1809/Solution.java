package com.javarush.task.task18.task1809;

/* 
Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке.
Закрыть потоки.


Требования:
1. Программа должна два раза считать имена файлов с консоли.
2. Для чтения из файла используй поток FileInputStream, для записи в файл - FileOutputStream
3. Во второй файл нужно записать все байты из первого в обратном порядке.
4. Потоки FileInputStream и FileOutputStream должны быть закрыты.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            FileInputStream fileInputStream = new FileInputStream(bufferedReader.readLine());
            FileOutputStream fileOutputStream = new FileOutputStream(bufferedReader.readLine());
            while (fileInputStream.available()>0){
                byte[] buffer = new byte[fileInputStream.available()];
                int c = fileInputStream.read(buffer);
                for(int i=buffer.length - 1; i>=0; i--){
                    fileOutputStream.write(buffer[i]);
                }
            }
            fileInputStream.close();
            fileOutputStream.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
