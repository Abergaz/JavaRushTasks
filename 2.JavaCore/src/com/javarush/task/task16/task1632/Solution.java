package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/*
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. Нить 1 должна бесконечно выполняться;
1.2. Нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. Нить 3 должна каждые полсекунды выводить "Ура";
1.4. Нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. Нить 5 должна читать с консоли числа пока не введено слово "N", а потом вывести в консоль сумму введенных чисел.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.

Подсказка:
Нить 4 можно проверить методом isAlive()


Требования:
1. Статический блок класса Solution должен создавать и добавлять 5 нитей в список threads.
2. Нити из списка threads не должны стартовать автоматически.
3. Нить 1 из списка threads должна бесконечно выполняться.
4. Нить 2 из списка threads должна выводить "InterruptedException" при возникновении исключения InterruptedException.
5. Нить 3 из списка threads должна каждые полсекунды выводить "Ура".
6. Нить 4 из списка threads должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться.
7. Нить 5 из списка threads должна читать с консоли числа пока не введено слово "N", а потом вывести в консоль сумму введенных чисел.
 */

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);
    static {
        threads.add(new OneThread());
        threads.add(new TwoThread());
        threads.add(new ThreeThread());
        threads.add(new FourThread());
        threads.add(new FiveThread());
    }

    public static void main(String[] args) {
    }
    public static class OneThread extends Thread{

        @Override
        public void run() {
           while (true){
           }
        }
    }
    public static class TwoThread extends Thread{

        @Override
        public void run() {
           try {
               throw new InterruptedException();
           }
           catch (InterruptedException e){
               System.out.println("InterruptedException");
           }
        }
    }
    public static class ThreeThread extends Thread{

        @Override
        public void run() {
            while (true){
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static class FourThread extends Thread implements Message{

        @Override
        public void showWarning() {
            this.interrupt();
        }

        @Override
        public void run() {
            while (!this.isInterrupted()) {  }
        }

    }
    public static class FiveThread extends Thread{
        @Override
        public void run() {
            BufferedReader  bufferedReader;
            int summ=0;

            bufferedReader= new BufferedReader(new InputStreamReader(System.in));
            String s="";
            try {
                s=bufferedReader.readLine();
                while (!s.equals("N")){
                    summ+=Integer.parseInt(s);
                    s=bufferedReader.readLine();
                }
                System.out.println(summ);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}