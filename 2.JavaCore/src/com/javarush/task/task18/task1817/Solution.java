package com.javarush.task.task18.task1817;

/* 
Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45.
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой.
4. Закрыть потоки.


Требования:
1. Считывать с консоли ничего не нужно.
2. Создай поток чтения из файла, который приходит первым параметром в main.
3. Посчитай отношение пробелов ко всем символам в файле и выведи в консоль это число.
4. Выведенное значение необходимо округлить до 2 знаков после запятой.
5. Поток чтения из файла должен быть закрыт.
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.Format;

public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        FileInputStream fileInputStream = new FileInputStream(file);
        int b=0;
        int nb=0;
        int r;
        while ((r=fileInputStream.read())!=-1){
            if (r==32) b++;
            else nb++;
        }
        fileInputStream.close();
        System.out.printf("%.2f",b/((double)(nb+b)) *100);
    }
}
