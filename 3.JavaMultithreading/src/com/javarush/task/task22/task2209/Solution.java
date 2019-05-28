package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Составить цепочку слов
Составить цепочку слов
В методе main считай с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставь все слова в таком порядке, чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
Вывести полученную строку на экран.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена


Требования:
1. Метод main должен считывать имя файла с клавиатуры.
2. В классе Solution не должно быть статических полей.
3. В методе getLine должен быть использован StringBuilder.
4. Метод getLine должен возвращать пустую строку(пустой StringBuilder) в случае если ему не были переданы параметры(слова).
5. Метод getLine не должен изменять переданные ему параметры(слова).
6. Все слова переданные в метод getLine должны быть включены в результирующую строку, если это возможно.
7. Вывод на экран должен соответствовать условию задачи.
*/
public class Solution {
    public static void main(String[] args) {
        //...
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> words = new TreeSet<String>();
        try {
            String fileName = bufferedReader.readLine();
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                for (String w : readLine.split("\\s")) {
                    words.add(w);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arr = new String[words.size()];
        Iterator<String> iterator = words.iterator();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = iterator.next();
        }

        StringBuilder result = getLine(arr);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        if (words == null || words.length == 0) return result;
        if (words.length == 1 || words[0].equals("")) return result.append(words[0]);

        ArrayList<String> wordsList = new ArrayList<>();

        wordsList.addAll(Arrays.asList(words));
        while (wordsList.remove("")) {
            wordsList.remove("");
        }
        while (isYes(wordsList)) {
            Collections.shuffle(wordsList);
        }
        for (String word : wordsList) {
            result.append(word).append(" ");
        }
        result.deleteCharAt(result.length() - 1);
        return result;
    }

    public static boolean isYes(ArrayList<String> wordsList) {
        for (int i = 0; i < wordsList.size() - 1; i++) {
            String firstWord = wordsList.get(i).toLowerCase();
            String secondWord = wordsList.get(i + 1).toLowerCase();
            if (firstWord.charAt(firstWord.length() - 1) != secondWord.charAt(0)) return true;
        }
        return false;
    }
}


