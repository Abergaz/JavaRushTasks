package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом.

Для каждого имени посчитать сумму всех его значений.
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени.
Закрыть потоки.

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0


Требования:
1. Программа НЕ должна считывать данные с консоли.
2. Программа должна считывать содержимое файла (используй FileReader).
3. Поток чтения из файла (FileReader) должен быть закрыт.
4. Программа должна выводить в консоль каждое имя и сумму всех его значений, все данные должны быть отсортированы в возрастающем порядке по имени
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(args[0]);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        TreeMap<String,Double> treeMap = new TreeMap<String, Double>();
        String r="";
        String name="";
        String money="";
        while (bufferedReader.ready()){
            r=bufferedReader.readLine();
            name = r.substring(0, r.toString().indexOf(" "));
            money = r.toString().substring(r.indexOf(" ") + 1, r.length());
            if (treeMap.containsKey(name)) {
                 treeMap.replace(name, treeMap.get(name) + Double.parseDouble(money));
            } else {
                treeMap.put(name, Double.parseDouble(money));
            }
        }
        fileReader.close();
        for (Map.Entry<String,Double> entry: treeMap.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }
}
