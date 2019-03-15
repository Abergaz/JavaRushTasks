package com.javarush.task.task08.task0815;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* 
Перепись населения
Создать словарь (Map<String, String>) занести в него десять записей по принципу "Фамилия" - "Имя".
Проверить сколько людей имеют совпадающие с заданным именем или фамилией.


Требования:
1. Программа не должна выводить текст на экран.
2. Программа не должна считывать значения с клавиатуры.
3. Метод createMap() должен создавать и возвращать словарь HashMap с типом элементов String, String состоящих из 10 записей по принципу «Фамилия» - «Имя».
4. Метод getCountTheSameFirstName() должен возвращать число людей у которых совпадает имя.
5. Метод getCountTheSameLastName() должен возвращать число людей у которых совпадает фамилия.
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        //напишите тут ваш код
        HashMap<String,String> hashMap = new HashMap<String, String>();
        String[] name = {"Иван", "Петр", "Василий", "Александр", "Игорь","Алексей","Руслан","Владимир","Дмитрий","Сергей"};
        String[] fname = {"Иванов", "Петров", "Сидоров", "Крол", "Ляхов","Зобков","Синьков","Богданов","Графин","Гуськов"};
        String mkey="";
        String mvalue="";
        while (hashMap.size()<10){
            mkey=fname[(int)(Math.random()*10)];
            mvalue=name[(int)(Math.random()*10)];
            hashMap.put(mkey,mvalue);
        }
        return hashMap;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name) {
        //напишите тут ваш код
        int n=0;
        Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            if (iterator.next().getValue().equals(name)) n++;
        }
        return n;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName) {
        //напишите тут ваш код
        int n=0;
        Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            if (iterator.next().getKey().equals(lastName)) n++;
        }
        return n;

    }

    public static void main(String[] args) {
        Solution.createMap();
    }
}
