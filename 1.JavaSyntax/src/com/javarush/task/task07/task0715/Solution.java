package com.javarush.task.task07.task0715;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 
Продолжаем мыть раму
1. Создай список из слов "мама", "мыла", "раму".
2. После каждого слова вставь в список строку, содержащую слово "именно".
3. Вывести результат на экран, каждый элемент списка с новой строки.


Требования:
1. Программа не должна считывать данные с клавиатуры.
2. Объяви переменную типа список строк и сразу проинициализируй ee.
3. Добавь в список слова: «мама», «мыла», «раму».
4. После каждого слова добавь в список строку, содержащую слово «именно».
5. Выведи элементы списка на экран, каждый с новой строки.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> arrayList = new ArrayList<String>();
        Collections.addAll(arrayList,"мама","мыла","раму");
        for(int i=1; i<=arrayList.size();i+=2){
            arrayList.add(i,"именно");
        }
        for (String x:arrayList) {
            System.out.println(x);
        }
    }
}
