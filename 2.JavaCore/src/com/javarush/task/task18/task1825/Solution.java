package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
Собираем файл из кусочков.
Считывать с консоли имена файлов.
Каждый файл имеет имя: [someName].partN.

Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.

Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end".
В папке, где находятся все прочтенные файлы, создать файл без суффикса [.partN].

Например, Lion.avi.

В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки.


Требования:
1. Программа должна считывать имена файлов с консоли, пока не будет введено слово "end".
2. Создай поток для записи в файл без суффикса [.partN] в папке, где находятся все считанные файлы.
3. В новый файл перепиши все байты из файлов-частей *.partN.
4. Чтение и запись должны происходить с использованием буфера.
5. Созданные для файлов потоки должны быть закрыты.
6. Не используй статические переменные.
*/

public class Solution {
    public static void main(String[] args) {
        TreeMap<Integer,String> treeMap = new TreeMap<Integer, String>();
        File file;
        BufferedInputStream bufferedInputStream;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String read;
        String endFileName;
        File endFile;

        try {
            while (!(read=bufferedReader.readLine()).equals("end")){
                if (!read.trim().equals("")) {
                    int a = read.lastIndexOf(".part");
                    if (a!=-1) {
                        a += 5;
                        int b = read.length();
                        String n = read.substring(a, b);
                        treeMap.put(Integer.parseInt(n), read);
                    }
                }
            }
            bufferedReader.close();

            file=new File(treeMap.firstEntry().getValue());
            endFileName=file.getPath().substring(0,file.getPath().lastIndexOf("."));
            endFile = new File(endFileName);

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(endFile),1024);

            for(Map.Entry<Integer,String> entry: treeMap.entrySet()){
                file = new File(entry.getValue());
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1024);
                int r;
                while ((r=bufferedInputStream.read())!=-1){
                    bufferedOutputStream.write(r);
                }
                bufferedInputStream.close();
            }
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
