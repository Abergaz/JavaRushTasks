package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри, а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.


Требования:
1. В методе main создай ZipInputStream для архивного файла (второй аргумент main). Нужно вычитать из него все содержимое.
2. В методе main создай ZipOutputStream для архивного файла (второй аргумент main).
3. В ZipOutputStream нужно записать содержимое файла, который приходит первым аргументом в main.
4. В ZipOutputStream нужно записать все остальное содержимое, которое было вычитано из ZipInputStream.
5. Потоки для работы с архивом должны быть закрыты.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        HashMap<ZipEntry, ByteArrayOutputStream> hashMap = new HashMap<ZipEntry, ByteArrayOutputStream>();
        Path fileName = Paths.get(args[0]);
        Path newFile = Paths.get("new\\"+fileName.getFileName().toString());
        //Path zip = Paths.get(args[1]);

        FileInputStream fileInputStream = new FileInputStream(args[1]);
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);

        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry())!=null){
            if (!zipEntry.getName().equals(newFile.toString())){
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipInputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, count);
                }
                byteArrayOutputStream.close();
                zipInputStream.closeEntry();
                hashMap.put(zipEntry,byteArrayOutputStream);
            }
        }
        zipInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream(args[1]);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        String s =newFile.toString();
        zipOutputStream.putNextEntry(new ZipEntry(s));
        Files.copy(Paths.get(args[0]),zipOutputStream);
        zipOutputStream.closeEntry();

        for (HashMap.Entry<ZipEntry,ByteArrayOutputStream> entry: hashMap.entrySet()){
            zipOutputStream.putNextEntry(new ZipEntry(entry.getKey().getName()));
            zipOutputStream.write(entry.getValue().toByteArray());
            zipOutputStream.closeEntry();
        }

        zipOutputStream.close();
    }
}
