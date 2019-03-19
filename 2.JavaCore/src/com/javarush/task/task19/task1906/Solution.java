package com.javarush.task.task19.task1906;

/* 
Четные символы
Считать с консоли 2 имени файла.
Вывести во второй файл все символы с четным порядковым номером (нумерация начинается с 1).

Пример первого файла:
text in file
Вывод во втором файле:
eti ie
Закрыть потоки ввода-вывод


Требования:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй FileReader c конструктором String).
4. Поток чтения из файла (FileReader) должен быть закрыт.
5. Программа должна записывать во второй файл все байты из первого файла с четным порядковым номером (используй FileWriter).
6. Поток записи в файл (FileWriter) должен быть закрыт.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileI = bufferedReader.readLine();
        String fileO = bufferedReader.readLine();
        bufferedReader.close();
        FileReader fileReader = new FileReader(fileI);
        FileWriter fileWriter = new FileWriter(fileO);
        int i=1;
        int r;
        while (fileReader.ready()){
            r=fileReader.read();
            if (i==2){
                fileWriter.write(r);
                i=1;
            }
            else {
                i=2;
            }

        }
        fileReader.close();
        fileWriter.close();
    }
}
