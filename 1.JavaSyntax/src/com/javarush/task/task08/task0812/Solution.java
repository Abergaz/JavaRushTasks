package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
1. Создай список чисел.
2. Добавь в список 10 чисел с клавиатуры.
3. Вывести на экран длину самой длинной последовательности повторяющихся чисел в списке.

Пример для списка 2, 4, 4, 4, 8, 8, 4, 12, 12, 14:
3
1, 1, 1, 4, 4, 4, 4, 4, 4, 4.
7

Искомое значение равно 3, т.к. самая длинная последовательность повторяющихся чисел состоит из трех четверок.


Требования:
1. Программа должна выводить число на экран.
2. Программа должна считывать значения с клавиатуры.
3. В методе main объяви переменную типа ArrayList с типом элементов Integer и сразу проинициализируй ee.
4. Программа должна добавлять в коллекцию 10 чисел, согласно условию.
5. Программа должна выводить на экран длину самой длинной последовательности повторяющихся чисел в списке.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int max=0;
        int l=1;
        for (int i=0; i<10; i++){
            arrayList.add(Integer.parseInt(bufferedReader.readLine()));
        }
        for (int i=0; i<9; i++){
            if (arrayList.get(i).equals(arrayList.get(i+1))){
                l++;
            }
            else {
                max = (max<l) ? l : max ;
                l=1;
            }
        }
        max = (max<l) ? l : max ;

        System.out.println(max);

    }
}