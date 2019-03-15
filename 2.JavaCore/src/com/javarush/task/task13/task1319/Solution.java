package com.javarush.task.task13.task1319;

import java.io.*;



/* 
Писатель в файл с консоли
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой строки.


Требования:
1. Программа должна считывать c консоли имя файла.
2. Создай и используй объект типа BufferedWriter.
3. Программа не должна ничего читать из файловой системы.
4. Программа должна считывать строки с консоли, пока пользователь не введет строку "exit".
5. Программа должна записать абсолютно все введенные строки (включая "exit") в файл, каждую строчку с новой строки.
6. Метод main должен закрывать объект типа BufferedWriter после использования.
7. Метод main не должен выводить данные на экран.
*/

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName=null;
        File file = null;
        FileWriter fileWriter=null;
        try {
            fileName = bufferedReader.readLine();
            file = new File(fileName);
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while (true){
                String rl= null;
                rl = bufferedReader.readLine();
                bufferedWriter.write(rl);
                bufferedWriter.newLine();
                if (rl.equals("exit")) break;
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
