package com.javarush.task.task20.task2025;

import java.sql.Date;
import java.util.*;

/*
Алгоритмы-числа
Число S состоит из M цифр, например, S=370 и M (количество цифр) = 3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания.

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.


Требования:
1. В классе Solution должен присутствовать метод getNumbers c одним параметром типа long.
2. Метод getNumbers должен быть публичным.
3. Метод getNumbers должен быть статическим.
4. Метод getNumbers должен возвращать массив чисел удовлетворяющих условию задачи.
*/
public class Solution {
    static long[][] pow;
    static {
        pow = new long[10][19];
        for (int i=0;i<10;i++){
            for (int j=0; j<19; j++){
                if(i==0){
                    pow[i][j]=1;
                }else if (j==0){
                    pow[i][j]=1;
                }else{
                    pow[i][j]=pow[i][j-1]*i;
                }
            }
        }
    }
    public static long[] getNumbers(long N) {
        long[] result = new long[100];
        long l=1;
        int count=0;
        while (l<=N){
            long summ=0;
            char[] chars=String.valueOf(l).toCharArray();
            int length = chars.length;
            ArrayList<Byte> arrayList1= new ArrayList<Byte>();
            for (char c: chars){
                arrayList1.add(Byte.parseByte(String.valueOf(c)));
            }
            for (byte ll: arrayList1){
                summ+=pow[ll][length];
            }

            if(Long.compare(summ,l)==0){
                System.out.println(l);
                result[count]=l;
                count++;
            }
            l++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Calendar.getInstance().getTime().toString());
        for (long l=0; l<=Long.MAX_VALUE;l++){

        }
        System.out.println(Calendar.getInstance().getTime().toString());
        /*
       long[] a=getNumbers(Long.MAX_VALUE);
       int i=0;
       while (i<a.length && a[i]!=0){
           System.out.println(a[i]);
           i++;
       }
       */
    }
}
