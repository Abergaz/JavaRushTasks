package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
Напиши класс Human с 6 полями.
Придумай и реализуй 10 различных конструкторов для него.
Каждый конструктор должен иметь смысл.


Требования:
1. Программа не должна считывать данные с клавиатуры.
2. В классе Human должно быть 6 полей.
3. Все поля класса Human должны быть private.
4. В классе Human должно быть 10 конструкторов.
5. Все конструкторы класса Human должны быть public.
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        // Напишите тут ваши переменные и конструкторы
        private byte b;
        private short s;
        private  char c;
        private  int i;
        private  float f;
        private double d;

        public Human(){};
        public Human(byte b){
            this();
            this.b=b;
        }
        public Human(short s){
            this();
            this.s=s;
        }
        public Human(char c){
            this();
            this.c=c;
        }
        public Human(int i){
            this();
            this.i=i;
        }
        public Human(float f){
            this();
            this.f=f;
        }
        public Human(double d){
            this();
            this.d=d;
        }
        public Human(byte b, short s ){
            this(b);
            this.s=s;
        }
        public Human(byte b, short s, char c ){
            this(b,s);
            this.c=c;
        }
        public Human(byte b, short s, char c, int i){
            this(b,s,c);
            this.i=i;
        }
    }
}
