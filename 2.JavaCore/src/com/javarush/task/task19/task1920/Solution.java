package com.javarush.task.task19.task1920;

/* 
Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом.

Для каждого имени посчитать сумму всех его значений.
Вывести в консоль имена в алфавитном порядке, у которых максимальная сумма.
Имена разделять пробелом либо выводить с новой строки.
Закрыть потоки.

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров


Требования:
1. Программа НЕ должна считывать данные с консоли.
2. Программа должна считывать содержимое файла (используй FileReader).
3. Поток чтения из файла (FileReader) должен быть закрыт.
4. Программа должна выводить в консоль имена, у которых максимальная сумма.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        bufferedReader.close();
        fileReader.close();
        Double max=Double.MIN_VALUE;
        for (Map.Entry<String,Double> entry: treeMap.entrySet()) {
            if (max<entry.getValue()){
                max=entry.getValue();
            }
        }
        for (Map.Entry<String,Double> entry: treeMap.entrySet()) {
            if (max.compareTo(entry.getValue())==0){
                System.out.println(entry.getKey());
            }
        }

    }
}
