package com.javarush.task.task31.task3106;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002


Требования:
1. В методе main нужно создать ZipInputStream для архива, собранного из кусочков файлов. Файлы приходят аргументами в main, начиная со второго.
2. Создай поток для записи в файл, который приходит первым аргументом в main. Запиши туда содержимое файла из архива.
3. Поток для чтения из архива должен быть закрыт.
4. Поток для записи в файл должен быть закрыт.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path resultFileName = Paths.get(args[0]);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i=1; i<args.length;i++){
            byteArrayOutputStream.write(Files.readAllBytes(Paths.get(args[i])));
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        byteArrayOutputStream.reset();

        ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream);
        ZipEntry entry;
        while((entry=zipInputStream.getNextEntry())!=null){
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = zipInputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, count);
            }
        }
        byteArrayOutputStream.close();
        Files.write(resultFileName,byteArrayOutputStream.toByteArray());
    }
}
