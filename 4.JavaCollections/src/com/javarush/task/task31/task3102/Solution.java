package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/* 
Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используй очередь, рекурсию не используй.
Верни список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.


Требования:
1. Метод getFileTree должен принимать аргументом String root, по которому нужно найти все вложенные файлы.
2. Метод getFileTree должен возвращать список строк.
3. Нужно реализовать метод getFileTree: найти все файлы по указанному пути и добавить их в список.
4. Метод getFileTree должен быть вызван только 1 раз (рекурсию не использовать).
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>();
        LinkedList<File> files = new LinkedList<File>();
        File file = new File(root);
        if (!file.isDirectory()){
            list.add(file.getAbsolutePath());
            file=new File(file.getParent());
        }
        if (file.isDirectory()){
            if (file.listFiles()!=null) {
                Collections.addAll(files, file.listFiles());
            }
           while (files.size()>0){
                File child = files.get(0);
                if (child.isDirectory()){
                     if (child.listFiles()!=null) {
                         Collections.addAll(files, child.listFiles());
                     }
                    files.remove(child);
                }else{
                    list.add(child.getAbsolutePath());
                    files.remove(child);
                }
               }
           }
        return  list;
    }

    public static void main(String[] args) throws IOException {
        List<String> list = getFileTree("d:\\Test\\test.txt");
        for (String s: list){
            System.out.println(s);
        }
    }
}
