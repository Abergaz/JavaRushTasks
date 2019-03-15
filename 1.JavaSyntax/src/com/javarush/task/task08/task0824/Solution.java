package com.javarush.task.task08.task0824;

/* 
Собираем семейство
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Выведи все объекты Human на экран (Подсказка: используй метод toString() класса Human).


Требования:
1. Программа должна выводить текст на экран.
2. Класс Human должен содержать четыре поля.
3. Класс Human должен содержать один метод.
4. Класс Solution должен содержать один метод.
5. Программа должна создавать объекты и заполнять их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей и выводить все объекты Human на экран.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код

        Human c1 = new Human("Загреба Сергей", true,33,new ArrayList<Human>());
        Human c2 = new Human("Загреба Александр", true,33, new ArrayList<Human>());
        Human c3 = new Human("Загреба Антон", true,17, new ArrayList<Human>());
        Human f = new Human("Загреба Роман", true,56,new ArrayList<Human>(Arrays.asList(c1,c2,c3)));
        Human m = new Human("Загреба Марина", false,53,new ArrayList<Human>(Arrays.asList(c1,c2,c3)));
        Human gf1 = new Human("Загреба Петр", true,78, new ArrayList<Human>(Arrays.asList(f)));
        Human gm1 = new Human("Загреба Мария", false,70, new ArrayList<Human>(Arrays.asList(f)));
        Human gf2 = new Human("Новиков Александр", true,85, new ArrayList<Human>(Arrays.asList(m)));
        Human gm2 = new Human("Новикова Александра", false,75, new ArrayList<Human>(Arrays.asList(m)));

        ArrayList<Human> arrayList= new ArrayList<Human>(Arrays.asList(c1, c2, c3, f, m, gf1, gm1, gf2, gm2));

        for (Human human: arrayList){
            System.out.println(human);
        }

    }

    public static class Human {
        //напишите тут ваш код
         String name;
         boolean sex;
          int age;
         ArrayList<Human> children;

        public Human(){
        }
        public Human(String name, boolean sex,int age){
            this.name = name;
            this.sex = sex;
            this.age = age;
        }
        public Human(String name, boolean sex,int age, ArrayList<Human> arrayList){
            this(name,sex,age);
            this.children=arrayList;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (!this.children.isEmpty()){


            int childCount = this.children.size();
           if (childCount > 0) {
                    text += ", дети: " + this.children.get(0).name;

                    for (int i = 1; i < childCount; i++) {
                        Human child = this.children.get(i);
                        text += ", " + child.name;
                    }
                }

            }
            return text;
        }
    }

}
