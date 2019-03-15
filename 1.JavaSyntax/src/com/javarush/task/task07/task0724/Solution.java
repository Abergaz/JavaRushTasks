package com.javarush.task.task07.task0724;

/* 
Семейная перепись
Создай класс Human с полями имя(String), пол(boolean), возраст(int), отец(Human), мать(Human). Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.

Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.

Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
...


Требования:
1. Программа не должна считывать данные с клавиатуры.
2. Добавить в класс Human поля: имя(String), пол(boolean), возраст(int), отец(Human), мать(Human).
3. Добавить в класс конструктор public Human(String name, boolean sex, int age).
4. Добавить в класс конструктор public Human(String name, boolean sex, int age, Human father, Human mother).
5. Создай 9 разных объектов типа Human (4 объекта без отца и матери и 5 объектов с ними)).
6. Выведи созданные объекты на экран.
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        ArrayList<Human> arrayList = new ArrayList<Human>();
        Human gf1= new Human("Александр", true, 85);
        arrayList.add(gf1);
        Human gf2= new Human("Петр", true, 80);
        arrayList.add(gf2);
        Human gm1= new Human("Александра", false, 75);
        arrayList.add(gm1);
        Human gm2= new Human("Мария", false, 70);
        arrayList.add(gm2);
        Human m = new Human("Марина", false, 53, gf1, gm1);
        arrayList.add(m);
        Human f =new Human("Роман", true, 56,gf2, gm2);
        arrayList.add(f);
        Human c1 = new Human("Сергей", true, 33, f,m);
        arrayList.add(c1);
        Human c2 = new Human("Александр", true, 33, f,m);
        arrayList.add(c2);
        Human c3 = new Human("Антон", true, 17, f,m);
        arrayList.add(c3);

        for (Human h: arrayList) {
            System.out.println(h);
        }

    }

    public static class Human {
        // напишите тут ваш код
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;

        public Human(String name, boolean sex, int age){
            this.name=name;
            this.sex=sex;
            this.age=age;
        }
        public Human(String name, boolean sex, int age, Human father, Human mother){
            this(name,sex,age);
            this.father=father;
            this.mother=mother;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setFather(Human father) {
            this.father = father;
        }

        public void setMother(Human mother) {
            this.mother = mother;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public Human getFather() {
            return father;
        }

        public Human getMother() {
            return mother;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }
}