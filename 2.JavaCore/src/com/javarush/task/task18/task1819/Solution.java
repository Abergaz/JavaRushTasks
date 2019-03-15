package com.javarush.task.task18.task1819;

/* 
Объединение файлов
Считать с консоли 2 имени файла.
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов.
Закрыть потоки.


Требования:
1. Программа должна два раза считать имена файлов с консоли.
2. Не используй в программе статические переменные.
3. Для первого файла создай поток для чтения и считай его содержимое.
4. Затем, для первого файла создай поток для записи(поток для записи должен быть один). Для второго - для чтения.
5. Содержимое первого и второго файла нужно объединить в первом файле.
6. Сначала должно идти содержимое второго файла, затем содержимое первого.
7. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1;
        String fileName2;
        FileInputStream fileInputStream;
        try {
            fileName1=bufferedReader.readLine();
            fileName2=bufferedReader.readLine();
            fileInputStream = new FileInputStream(fileName1);
            int r;
            byte[] buffer =new byte[fileInputStream.available()];
            while (fileInputStream.available()>0){
                fileInputStream.read(buffer);
            }
            fileInputStream.close();

            FileOutputStream fileOutputStream = new FileOutputStream(fileName1);
            fileInputStream = new FileInputStream(fileName2);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            while ((r=bufferedInputStream.read())!=-1){
                fileOutputStream.write(r);
            }
            fileOutputStream.write(buffer);

            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
