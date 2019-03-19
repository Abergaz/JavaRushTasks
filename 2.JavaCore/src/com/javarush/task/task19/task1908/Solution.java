package com.javarush.task.task19.task1908;

/* 
Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки.

Пример тела файла:
12 text var2 14 8ю 1

Результат:
12 14 1


Требования:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй BufferedReader c конструктором принимающим FileReader).
4. Поток чтения из файла (BufferedReader) должен быть закрыт.
5. Программа должна записывать во второй файл все числа, через пробел, из первого файла (используй BufferedWriter с конструктором FileWriter).
6. Поток записи в файл (BufferedWriter) должен быть закрыт.
*/

import java.io.*;



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileI = bufferedReader.readLine();
        String fileO = bufferedReader.readLine();
        bufferedReader.close();
        FileReader fileReader = new FileReader(fileI);
        bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        int r;
        while (bufferedReader.ready()) {
            r = bufferedReader.read();
            stringBuilder.append((char)r);
        }
        bufferedReader.close();
        fileReader.close();


        String[] arr= stringBuilder.toString().split("\\s");

        FileWriter fileWriter = new FileWriter(fileO);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (String s: arr) {
            try {
                Integer.parseInt(s);
                bufferedWriter.write(s);
                bufferedWriter.write(" ");
            }catch (Exception e){}

        }

        bufferedWriter.close();
        fileWriter.close();
    }
}
