package com.javarush.task.task12.task1207;

/* 
Int и Integer
Написать два метода: print(int) и print(Integer).
Написать такой код в методе main, чтобы вызвались они оба.


Требования:
1. Класс Solution должен содержать статический метод main().
2. Класс Solution должен содержать статический void метод print() с параметром типа int.
3. Класс Solution должен содержать статический void метод print() с параметром типа Integer.
4. Метод main() должен вызывать метод print() с параметром типа int.
5. Метод main() должен вызывать метод print() с параметром типа Integer.
*/

public class Solution {
    public static void main(String[] args) {
        int i=1;
        Integer I=1;
        print(i);
        print(I);

    }

    //Напишите тут ваши методы
    public static void print(int i){};
    public static void print(Integer i){};
}
