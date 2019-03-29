package com.javarush.task.task31.task3111;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private  List<Path> foundFiles = new ArrayList<Path>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        //Если в SearchFileVisitor задан критерий поиска partOfName, метод visitFile должен добавить файл в foundFiles, если в названии содержится строка partOfName.
        boolean containsName = true;
        if (partOfName != null && !file.getFileName().toString().contains(partOfName)) {
            containsName = false;
        }

        //Если в SearchFileVisitor задан критерий поиска partOfContent, метод visitFile должен добавить файл в foundFiles, если в содержимом встречается строка partOfContent.
        byte[] contentB = Files.readAllBytes(file); // Читаем массив байт из файла
        String contentS = new String(contentB); // байты в строку
        boolean containsContent = true;
        if (partOfContent != null && !contentS.contains(partOfContent)){
            containsContent = false;
        }

        //Если в SearchFileVisitor задан критерий поиска maxSize, метод visitFile должен добавить файл в foundFiles, если размер файла меньше maxSize.
        boolean containsMaxSize = true;
        if (maxSize!= 0 && contentB.length>maxSize){
            containsMaxSize = false;
        }

        //Если в SearchFileVisitor задан критерий поиска minSize, метод visitFile должен добавить файл в foundFiles, если размер файла больше minSize.
        boolean containsMinSize = true;
        if (minSize!= 0 && contentB.length<minSize){
            containsMinSize = false;
        }

        if(containsName && containsContent && containsMaxSize && containsMinSize) {
            foundFiles.add(file);
        }

        return super.visitFile(file, attrs);
    }
}
