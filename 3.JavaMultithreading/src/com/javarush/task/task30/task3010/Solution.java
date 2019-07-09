package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
В метод main первым аргументом приходит строка, которая содержит последовательность символов (Все символы в строке имеют коды в таблице ASCII от 32 до 127 включительно).
Длина строки не превышает 255 символов. Нужно реализовать программу, которая по входящей строке определит, является ли содержимое строки записью числа в системе счисления
с основанием не больше 36 включительно. Если является - нужно вывести минимальное основание системы счисления, в которой это число может существовать.
Если не является - необходимо вывести "incorrect".
В системах счисления с основанием большим 10 в качестве цифр используются латинские буквы. К примеру, числу 35 в десятичной системе соответствует число "Z" в системе с основанием 36. Так как рассматриваем позиционные системы счисления - минимальное основание, которое должна выводить программа, это 2.
Если возникают любые исключения - перехватывай их и не выводи стек-трейс.

Пример1:
Вход:
00
Ожидаемый вывод:
2

Пример2:
Вход:
12AS08z
Ожидаемый вывод:
36

Пример3:
Вход:
12AS08Z/
Ожидаемый вывод:
incorrect


Требования:
1. Если возникло любое исключение - необходимо перехватить его и не выводить стек-трейс.
2. Если входящая строка содержит что-либо, кроме латинских букв и цифр - необходимо вывести "incorrect".
3. Если входящая строка корректна - необходимо вывести основание системы счисления.
4. Минимальное основание, которое выводит программа должно равняться 2.
5. Вывод программы должен соответствовать условию.
*/

import java.math.BigDecimal;
import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        try {
            for (int i = 2; i < 37; i++) {
                if (isNumber(args[0], i)) {
                    System.out.println(i);
                    break;
                } else {
                    if (i == 36) System.out.println("incorrect");
                }
            }
        } catch (Exception e) {
        }
    }

    public static boolean isNumber(String arg, int radix) {
        boolean result = false;
        try {
            new BigDecimal(new BigInteger(arg, radix));
            result = true;
        } catch (Exception e) {
        }
        return result;
    }
}