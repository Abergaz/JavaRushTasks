package com.javarush.task.task09.task0927;

import com.sun.javafx.collections.MappingChange;

import javax.swing.text.html.parser.Entity;
import java.security.KeyStore;
import java.util.*;

/* 
Десять котов
Есть класс кот - Cat, с полем "имя" (String).
Создать словарь Map<String, Cat> и добавить туда 10 котов в виде "Имя"-"Кот".
Получить из Map множество(Set) всех котов и вывести его на экран.


Требования:
1. Программа не должна считывать данные с клавиатуры.
2. Метод createMap должен создавать новый объект HashMap<String, Cat>.
3. Метод createMap должен добавлять в словарь 10 котов в виде «Имя»-«Кот».
4. Метод createMap должен возвращать созданный словарь.
5. Метод convertMapToSet должен создать и вернуть множество котов, полученных из переданного словаря.
6. Программа должна вывести всех котов из множества на экран.
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        //напишите тут ваш код
        Map<String, Cat> map = new HashMap<String, Cat>();
        map.put("A",new Cat("A"));
        map.put("B",new Cat("B"));
        map.put("C",new Cat("C"));
        map.put("D",new Cat("D"));
        map.put("E",new Cat("E"));
        map.put("F",new Cat("F"));
        map.put("G",new Cat("G"));
        map.put("J",new Cat("G"));
        map.put("K",new Cat("K"));
        map.put("L",new Cat("L"));


        return  map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        //напишите тут ваш код
        Set<Cat> set = new HashSet<Cat>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Cat> pair = (Map.Entry)iterator.next();
            set.add(pair.getValue());
        }
        return set;
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
