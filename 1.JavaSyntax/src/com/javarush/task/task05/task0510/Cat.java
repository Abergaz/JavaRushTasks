package com.javarush.task.task05.task0510;

/* 
Кошкоинициация
Создать класс Cat (кот) с пятью инициализаторами:
- Имя,
- Имя, вес, возраст
- Имя, возраст (вес стандартный)
- вес, цвет (имя, адрес и возраст неизвестны, это бездомный кот)
- вес, цвет, адрес (чужой домашний кот)

Задача инициализатора - сделать объект валидным.
Например, если вес неизвестен, то нужно указать какой-нибудь средний вес.
Кот не может ничего не весить.
То же касательно возраста.
А вот имени может и не быть (null).
То же касается адреса: null.
*/


public class Cat {
    //напишите тут ваш код
    private String name;
    private int age;
    private int weight;
    private String color;
    private String address;

    public void initialize(String name){
        this.name = name;
        this.age = 1;
        this.weight = 1;
        this.color="blue";
    }
    public void initialize(String name, int weight, int age){
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color="blue";
    }
    public void initialize(String name, int age){
        this.name = name;
        this.age = age;
        this.weight = 1;
        this.color="blue";
    }
    public void initialize(int weight, String color){
        this.weight = weight;
        this.color = color;
        this.age = 1;
    }
    public void initialize(int weight, String color, String address){
        this.weight = weight;
        this.color = color;
        this.address = address;
        this.age = 1;
    }

    public static void main(String[] args) {

    }
}
