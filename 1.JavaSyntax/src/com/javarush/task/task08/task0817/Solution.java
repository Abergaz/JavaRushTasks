package com.javarush.task.task08.task0817;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* 
Нам повторы не нужны
Создать словарь (Map<String, String>) занести в него десять записей по принципу "фамилия" - "имя".
Удалить людей, имеющих одинаковые имена.


Требования:
1. Программа не должна выводить текст на экран.
2. Программа не должна считывать значения с клавиатуры.
3. Метод createMap() должен создавать и возвращать словарь HashMap с типом элементов String, String состоящих из 10 записей.
4. Метод removeTheFirstNameDuplicates() должен удалять из словаря всех людей, имеющие одинаковые имена.
5. Метод removeTheFirstNameDuplicates() должен вызывать метод removeItemFromMapByValue().
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        //напишите тут ваш код
        String f;
        String n;
        int i=0;
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("1","1");
        hashMap.put("2","2");
        hashMap.put("3","3");
        hashMap.put("4","1");
        hashMap.put("5","5");
        hashMap.put("6","6");
        hashMap.put("7","7");
        hashMap.put("8","8");
        hashMap.put("9","6");
        hashMap.put("10","10");


        return hashMap;
    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        //напишите тут ваш код
        Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
        HashSet<String> hashSet = new HashSet<String>();

        while (iterator.hasNext()){
            Map.Entry<String,String> mapEntry = iterator.next();
            int count=0;
            Iterator<Map.Entry<String,String>> iterator2 = map.entrySet().iterator();
            while (iterator2.hasNext()){
                Map.Entry<String,String> mapEntry2 = iterator2.next();
                if (mapEntry.getValue().equals(mapEntry2.getValue()))
                    count++;
                if (count>1){
                    if (!hashSet.contains(mapEntry.getValue())) hashSet.add(mapEntry.getValue());
                    break;
                }
            }
        }
        for (String s: hashSet) {
            removeItemFromMapByValue(map,s);
        }


    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {

        Solution.removeTheFirstNameDuplicates(Solution.createMap());
    }
}
