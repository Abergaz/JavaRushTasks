package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Отслеживаем изменения
Считать с консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines.
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME.
Пустые строки даны в примере для наглядности.
В оригинальном и редактируемом файлах пустых строк нет!

Пример 1:
оригинальный    редактированный    общий
file1:          file2:             результат:(lines)

строка1         строка1            SAME строка1
строка2                            REMOVED строка2
строка3         строка3            SAME строка3
строка4                            REMOVED строка4
строка5         строка5            SAME строка5
                строка0            ADDED строка0
строка1         строка1            SAME строка1
строка2                            REMOVED строка2
строка3         строка3            SAME строка3
                строка4            ADDED строка4
строка5         строка5            SAME строка5
строка0                            REMOVED строка0

Пример 2:
оригинальный    редактированный    общий
file1:          file2:             результат:(lines)

строка1         строка1            SAME строка1
                строка0            ADDED строка0

Пустые строки в примере означают, что этой строки нет в определенном файле.


Требования:
1. Класс Solution должен содержать класс LineItem.
2. Класс Solution должен содержать enum Type.
3. Класс Solution должен содержать публичное статическое поле lines типа List<LineItem>, которое сразу проинициализировано.
4. В методе main(String[] args) программа должна считывать имена файлов с консоли (используй BufferedReader).
5. В методе main(String[] args) BufferedReader для считывания данных с консоли должен быть закрыт.
6. Программа должна считывать содержимое первого и второго файла (используй FileReader).
7. Потоки чтения из файлов (FileReader) должны быть закрыты.
8. Список lines должен содержать объединенную версию строк из файлов, где для каждой строки указана одна из операций ADDED, REMOVED, SAME.
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> arrayList1 = getArrayListFormFile();
        ArrayList<String> arrayList2 = getArrayListFormFile();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.close();

        for(int i=0; i<arrayList2.size(); ){
            if (arrayList1.size()==0){
                lines.add(new LineItem(Type.ADDED,arrayList2.get(i)));
                arrayList2.remove(i);
            }
            else if (arrayList2.get(i).equals(arrayList1.get(i))){
                lines.add(new LineItem(Type.SAME,arrayList2.get(i)));
                arrayList1.remove(i);
                arrayList2.remove(i);
            }else if(arrayList2.get(i).equals(arrayList1.get(i+1))){
                lines.add(new LineItem(Type.REMOVED,arrayList1.get(i)));
                lines.add(new LineItem(Type.SAME,arrayList2.get(i)));
                arrayList1.remove(i+1);
                arrayList1.remove(i);
                arrayList2.remove(i);;

            }else{
                lines.add(new LineItem(Type.ADDED,arrayList2.get(i)));
                lines.add(new LineItem(Type.SAME,arrayList1.get(i)));
                arrayList2.remove(i+1);
                arrayList2.remove(i);
                arrayList1.remove(i);;

            }
        }
        if (arrayList1.size()>0){
            lines.add(new LineItem(Type.REMOVED,arrayList1.get(0)));
        }
    }
    private static ArrayList<String> getArrayListFormFile() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(bufferedReader.readLine());
        bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> arrayList = new ArrayList<String>();
        while (bufferedReader.ready()){
            arrayList.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        fileReader.close();
        return arrayList;
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
