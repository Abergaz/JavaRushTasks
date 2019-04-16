package com.javarush.task.task36.task3605;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* 
Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортируй буквы по алфавиту и выведи на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то выведи их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрой потоки.

Пример 1 данных входного файла:
zBk yaz b-kN

Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB

Пример 2 вывода:
abc

Подсказка: использовать TreeSet


Требования:
1. Программа должна использовать класс TreeSet.
2. У объекта типа TreeSet вызови метод add для добавления элементов.
3. Программа должна выводить результат на экран.
4. Вывод программы должен соответствовать условию задачи.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(args[0]);
        String abc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<String> list = Files.readAllLines(path);
        TreeSet<Character> treeSet = new TreeSet<Character>();
        for (String s : list) {
            for (char c : s.toCharArray()) {
                if (abc.contains(String.valueOf(c))) {
                    treeSet.add(c);
                }
            }
        }
        int i = 0;
        for (Iterator<Character> it = treeSet.iterator(); it.hasNext() && i < 5; ) {
            System.out.print(it.next());
            i++;
        }
    }
}
