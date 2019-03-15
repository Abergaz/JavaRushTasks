package com.javarush.task.task18.task1818;

/* 
Два в одном
Считать с консоли 3 имени файла.
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла.
Закрыть потоки.


Требования:
1. Программа должна три раза считать имена файлов с консоли.
2. Для первого файла создай поток для записи. Для двух других - потоки для чтения.
3. Содержимое второго файла нужно переписать в первый файл.
4. Содержимое третьего файла нужно дописать в первый файл (в который уже записан второй файл).
5. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream1;
        FileInputStream fileInputStream2;
        BufferedInputStream bufferedInputStream;
        int r;
        try {
            fileOutputStream = new FileOutputStream(bufferedReader.readLine(),true);
            fileInputStream1 = new FileInputStream(bufferedReader.readLine());
            fileInputStream2 = new FileInputStream(bufferedReader.readLine());
            bufferedInputStream = new BufferedInputStream(fileInputStream1);
            while ((r=bufferedInputStream.read())!=-1){
                fileOutputStream.write(r);
            }
            fileInputStream1.close();
            bufferedInputStream = new BufferedInputStream(fileInputStream2);
            while ((r=bufferedInputStream.read())!=-1){
                fileOutputStream.write(r);
            }
            fileInputStream2.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
