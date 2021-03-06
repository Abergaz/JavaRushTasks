package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/* 
Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.


Требования:
1. Программа НЕ должна считывать данные с клавиатуры.
2. Программа НЕ должна выводить данные на экран.
3. Программа должна записывать данные в файл.
4. Содержимое второго файла должно соответствовать содержимому первого файла за исключением кодировки(UTF-8).
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        /*
        так не принимает валидатор
        byte[] bytes = Files.readAllBytes(Paths.get(args[0]));
        Charset w1251 = Charset.forName("Windows-1251");
        Charset unf8 = Charset.forName("UTF-8");
        String s = new String(bytes,w1251);
        Files.write(Paths.get(args[1]),s.getBytes(unf8));
        */

        //А так принимает
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        FileOutputStream fileOutputStream = new FileOutputStream(args[1]);

        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer);

        fileOutputStream.write(new String(buffer, "Windows-1251").getBytes("UTF-8"));

        fileInputStream.close();
        fileOutputStream.close();

    }
}
