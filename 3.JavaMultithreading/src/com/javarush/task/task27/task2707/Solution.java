package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
Реализуй логику метода isLockOrderNormal, который должен определять:
соответствует ли порядок synchronized блоков в методе someMethodWithSynchronizedBlocks - порядку передаваемых в него аргументов.
В случае, если сначала происходит синхронизация по o1, а потом по o2, метод должен вернуть true.
Если наоборот - false.


Требования:
1. Метод isLockOrderNormal должен возвращать true в случае, если синхронизация в методе someMethodWithSynchronizedBlocks происходит сначала по объекту o1, а потом по o2.
2. Метод isLockOrderNormal должен возвращать false в случае, если синхронизация в методе someMethodWithSynchronizedBlocks происходит сначала по объекту o2, а потом по o1.
3. Метод isLockOrderNormal НЕ должен быть приватным.
4. Класс Solution НЕ должен быть объявлен с модификатором final.
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        int lock1 = obj1.hashCode();
        int lock2 = obj2.hashCode();
        Object firstLock = lock1 > lock2 ? obj1 : obj2;
        Object secondLock = lock1 > lock2 ? obj2 : obj1;
        synchronized (firstLock) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            synchronized (secondLock) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception
    {
        new Thread() {
            @Override
            public void run() {
                synchronized (o1) {
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {

                    }
                    synchronized (o2) {
                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }
        }.start();

        Thread thread2=new Thread() {
            @Override
            public void run() {
                        solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };
        thread2.start();
        return Thread.State.BLOCKED.equals(thread2.getState());
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
