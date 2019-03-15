package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
Создать словарь (Map<String, Integer>) и занести в него десять записей по принципу: "фамилия" - "зарплата".
Удалить из словаря всех людей, у которых зарплата ниже 500.


Требования:
1. Программа не должна выводить текст на экран.
2. Программа не должна считывать значения с клавиатуры.
3. Метод createMap() должен создавать и возвращать словарь HashMap с типом элементов String, Integer состоящих из 10 записей по принципу «фамилия» - «зарплата».
4. Метод removeItemFromMap() должен удалять из словаря всех людей, у которых зарплата ниже 500.
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        //напишите тут ваш код
        HashMap<String,Integer> hashMap =new HashMap<String, Integer>();
        hashMap.put("1",100);
        hashMap.put("2",200);
        hashMap.put("3",300);
        hashMap.put("4",400);
        hashMap.put("5",500);
        hashMap.put("6",600);
        hashMap.put("7",700);
        hashMap.put("8",800);
        hashMap.put("9",900);
        hashMap.put("10",1000);
        return hashMap;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        Iterator<Map.Entry<String,Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            if (iterator.next().getValue()<500) iterator.remove();
        }
    }

    public static void main(String[] args) {

    }
}