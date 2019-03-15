package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/* 
Самые частые байты
Ввести с консоли имя файла.
Найти байт или байты с максимальным количеством повторов.
Вывести их на экран через пробел.
Закрыть поток ввода-вывода.


Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль через пробел должны выводиться все байты из файла с максимальным количеством повторов.
4. Данные в консоль должны выводится в одну строку.
5. Поток чтения из файла должен быть закрыт.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(bufferedReader.readLine());
        TreeMap<Integer,Integer> treeMap = new TreeMap<Integer, Integer>();
        int read=0;
        while (fileInputStream.available()>0){
            read = fileInputStream.read();
            if (treeMap.containsKey(read)){
                treeMap.replace(read, treeMap.get(read),treeMap.get(read)+1);
            }else{
                treeMap.put(read,1);
            }
        }
        fileInputStream.close();
        int max = treeMap.lastEntry().getValue();
        for (Map.Entry<Integer,Integer> entry :treeMap.entrySet()){
            if (entry.getValue()== max) {
                System.out.print(entry.getKey() + " ");
            }
        }
    }
}
