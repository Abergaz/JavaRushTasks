package com.javarush.task.task08.task0802;

/* 
HashMap из 10 пар
HashMap из 10 пар
Создать коллекцию HashMap<String, String>, занести туда 10 пар строк:
арбуз - ягода,
банан - трава,
вишня - ягода,
груша - фрукт,
дыня - овощ,
ежевика - куст,
жень-шень - корень,
земляника - ягода,
ирис - цветок,
картофель - клубень.
Вывести содержимое коллекции на экран, каждый элемент с новой строки.

Пример вывода (тут показана только одна строка):
картофель - клубень


Требования:
1. Объяви переменную коллекции HashMap с типом элементов String, String и сразу проинициализируй ee.
2. Программа не должна считывать значения с клавиатуры.
3. Программа должна добавлять в коллекцию 10 пар строк, согласно условию.
4. Программа должна выводить содержимое коллекции на экран, каждую пару с новой строки.
*/

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("арбуз", "ягода");
        hashMap.put("банан", "трава");
        hashMap.put("вишня","ягода");
        hashMap.put("груша","фрукт");
        hashMap.put("дыня","овощ");
        hashMap.put("ежевика","куст");
        hashMap.put("жень-шень","корень");
        hashMap.put("земляника","ягода");
        hashMap.put("ирис","цветок");
        hashMap.put("картофель","клубень");

        Iterator<Map.Entry<String,String>> iterator = hashMap.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String,String> mapEntry;
            mapEntry=iterator.next();
            System.out.println(mapEntry.getKey() + " - " + mapEntry.getValue());
        }
    }
}
