package com.javarush.task.task08.task0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Минимальное из N чисел
1. Ввести с клавиатуры число N.
2. Считать N целых чисел и заполнить ими список - метод getIntegerList.
3. Найти минимальное число среди элементов списка - метод getMinimum.


Требования:
1. Программа должна выводить текст на экран.
2. Программа должна считывать значения с клавиатуры.
3. Класс Solution должен содержать только три метода.
4. Метод getIntegerList() должен считать с клавиатуры число N, потом вернуть список размером N элементов, заполненный числами с клавиатуры.
5. Метод getMinimum() должен вернуть минимальное число среди элементов списка.
6. Метод main() должен вызывать метод getIntegerList().
7. Метод main() должен вызывать метод getMinimum()
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        List<Integer> integerList = getIntegerList();
        System.out.println(getMinimum(integerList));
    }

    public static int getMinimum(List<Integer> array) {
        // Найти минимум тут
        Integer[] a = array.toArray(new Integer[array.size()]);
        Arrays.sort(a);
        return a[0];
    }

    public static List<Integer> getIntegerList() throws IOException {
        // Создать и заполнить список тут
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(bufferedReader.readLine());
        ArrayList<Integer> arrayList = new ArrayList<Integer>(n);
        for (int i =0; i<n; i++){
            arrayList.add(Integer.parseInt(bufferedReader.readLine()));
        }
        return arrayList;
    }
}
