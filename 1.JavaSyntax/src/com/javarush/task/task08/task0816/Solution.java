package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Добрая Зинаида и летние каникулы
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: "фамилия" - "дата рождения".
Удалить из словаря всех людей, родившихся летом.


Требования:
1. Программа не должна выводить текст на экран.
2. Программа не должна считывать значения с клавиатуры.
3. Метод createMap() должен создавать и возвращать словарь HashMap с типом элементов String, Date состоящий из 10 записей.
4. Метод removeAllSummerPeople() должен удалять из словаря всех людей, родившихся летом.
*/

public class Solution {
    public static HashMap<String, Date> createMap() throws ParseException {
        DateFormat df = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", df.parse("JUNE 1 1980"));

        //напишите тут ваш код
        map.put("Иванов", df.parse("JUNE 1 1980"));
        map.put("Петров", df.parse("JUNE 1 1980"));
        map.put("Сидоров", df.parse("JUNE 1 1980"));
        map.put("Ляхов", df.parse("JUNE 1 1980"));
        map.put("Загреба", df.parse("JUNE 1 1980"));
        map.put("Зобков", df.parse("JUNE 1 1980"));
        map.put("Синьков", df.parse("JUNE 1 1980"));
        map.put("Балыкин", df.parse("JUNE 1 1980"));
        map.put("Богданов", df.parse("JUNE 1 1980"));

        return  map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map) {
        //напишите тут ваш код
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            int m=iterator.next().getValue().getMonth();
            if (m>=5 && m<=7) iterator.remove();
        }

    }

    public static void main(String[] args) {
        try {
            Solution.removeAllSummerPeople(Solution.createMap());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
