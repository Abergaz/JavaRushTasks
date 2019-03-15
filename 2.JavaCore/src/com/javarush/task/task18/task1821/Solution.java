package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете).

Пример:
','=44, 's'=115, 't'=116.

Вывести на консоль отсортированный результат:
[символ1] частота1
[символ2] частота2
Закрыть потоки.

Пример вывода:
, 19
- 7
f 361


Требования:
1. Считывать с консоли ничего не нужно.
2. Создай поток для чтения из файла, который приходит первым параметром в main.
3. В файле необходимо посчитать частоту встречания каждого символа и вывести результат.
4. Выведенный в консоль результат должен быть отсортирован по возрастанию кода ASCII.
5. Поток для чтения из файла должен быть закрыт.
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        File file = new File(args[0]);
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        TreeMap<Integer,Integer> treeMap = new TreeMap<Integer, Integer>();

        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            int r;
            while ((r=bufferedReader.read())!=-1){
                if(treeMap.containsKey(r)){
                    treeMap.replace(r,(treeMap.get(r)+1));
                }else{
                    treeMap.put(r,1);
                }
            }
            for (Map.Entry<Integer,Integer> entry: treeMap.entrySet()) {
                System.out.println(((char)entry.getKey().intValue())+ " " + entry.getValue());
            }

            bufferedReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
