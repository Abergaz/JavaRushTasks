package com.javarush.task.task18.task1808;

/* 
Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать большую часть.
Закрыть потоки.


Требования:
1. Программа должна три раза считать имена файлов с консоли.
2. Для чтения из файла используй поток FileInputStream, для записи в файлы - FileOutputStream
3. Первую половину байт из первого файла нужно записать во второй файл.
4. Вторую половину байт из первого файла нужно записать в третий файл.
5. Потоки FileInputStream и FileOutputStream должны быть закрыты.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream1;
        FileOutputStream fileOutputStream2;
        try {
            fileInputStream = new FileInputStream(bufferedReader.readLine());
            fileOutputStream1 = new FileOutputStream(bufferedReader.readLine());
            fileOutputStream2 = new FileOutputStream(bufferedReader.readLine());
            int count = fileInputStream.available();
            while (fileInputStream.available()>0){
                int c;
                byte[] buffer;
                if (count % 2 == 0){
                    count=count/2;
                    buffer = new byte[count];
                    c=fileInputStream.read(buffer);
                    fileOutputStream1.write(buffer);
                    c=fileInputStream.read(buffer);
                    fileOutputStream2.write(buffer);
                }else{
                    count=count/2;
                    buffer = new byte[count+1];
                    c=fileInputStream.read(buffer);
                    fileOutputStream1.write(buffer);
                    c=fileInputStream.read(buffer);
                    fileOutputStream2.write(buffer,0,c);
                }
            }
            fileInputStream.close();
            fileOutputStream1.close();
            fileOutputStream2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
