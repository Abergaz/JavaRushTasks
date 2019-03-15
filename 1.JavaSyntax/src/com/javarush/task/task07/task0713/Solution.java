package com.javarush.task.task07.task0713;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Играем в Jолушку
1. Введи с клавиатуры 20 чисел, сохрани их в список и рассортируй по трём другим спискам:
Число нацело делится на 3 (x%3==0), нацело делится на 2 (x%2==0) и все остальные.
Числа, которые делятся на 3 и на 2 одновременно, например 6, попадают в оба списка.
Порядок объявления списков очень важен.
2. Метод printList должен выводить на экран все элементы списка с новой строки.
3. Используя метод printList выведи эти три списка на экран. Сначала тот, который для x%3, потом тот, который для x%2, потом последний.


Требования:
1. Объяви и сразу проинициализируй 4 переменных типа ArrayList<Integer> (список целых чисел). Первый список будет главным, а остальные - дополнительными.
2. Считать 20 чисел с клавиатуры и добавить их в главный список.
3. Добавить в первый дополнительный список все числа из главного, которые нацело делятся на 3.
4. Добавить во второй дополнительный список все числа из главного, которые нацело делятся на 2.
5. Добавить в третий дополнительный список все остальные числа из главного.
6. Метод printList должен выводить на экран все элементы переданного списка, каждый с новой строки.
7. Программа должна вывести три дополнительных списка, используя метод printList.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        ArrayList<Integer> arrayList3= new ArrayList<Integer>();
        ArrayList<Integer> arrayList2= new ArrayList<Integer>();
        ArrayList<Integer> arrayListAny= new ArrayList<Integer>();
        int tmp;
        for (int i=0; i<20; i++){
            tmp=Integer.parseInt(bufferedReader.readLine());
            arrayList.add(tmp);
            if (tmp%3==0 && tmp%2==00){
                arrayList3.add(tmp);
                arrayList2.add(tmp);
            }
            else if(tmp%3==0){
                arrayList3.add(tmp);
            }
            else if(tmp%2==0){
                arrayList2.add(tmp);
            }
            else{
                arrayListAny.add(tmp);
            }
        }
        printList(arrayList3);
        printList(arrayList2);
        printList(arrayListAny);
    }

    public static void printList(List<Integer> list) {
        //напишите тут ваш код
        for (Integer x: list){
            System.out.println(x);
        }
    }
}
