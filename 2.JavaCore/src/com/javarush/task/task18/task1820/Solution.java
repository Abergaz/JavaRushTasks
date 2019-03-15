package com.javarush.task.task18.task1820;

/* 
Округление чисел
Считать с консоли 2 имени файла.
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415.
Округлить числа до целых и записать через пробел во второй файл.
Закрыть потоки.

Принцип округления:
3.49 => 3
3.50 => 4
3.51 => 4
-3.49 => -3
-3.50 => -3
-3.51 => -4


Требования:
1. Программа должна два раза считать имена файлов с консоли.
2. Для первого файла создай поток для чтения. Для второго - поток для записи.
3. Считать числа из первого файла, округлить их и записать через пробел во второй.
4. Должны соблюдаться принципы округления, указанные в задании.
5. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            FileInputStream fileInputStream = new FileInputStream(bufferedReader.readLine());
            FileOutputStream fileOutputStream = new FileOutputStream(bufferedReader.readLine());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder=new StringBuilder();
            String r;
            while ((r=bufferedReader.readLine())!=null){
                stringBuilder.append(r);
            }
            r=stringBuilder.toString();
            stringBuilder=new StringBuilder();
            for (String s:r.split(" ")){
                stringBuilder.append(Math.round(Double.parseDouble(s)));
                stringBuilder.append(" ");
            }
            bufferedWriter.write(stringBuilder.toString());
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
            bufferedWriter.close();
            outputStreamWriter.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
