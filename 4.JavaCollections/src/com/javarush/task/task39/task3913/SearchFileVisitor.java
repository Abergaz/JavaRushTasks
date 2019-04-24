package com.javarush.task.task39.task3913;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обхода папки и вложенных файлов и папок
 */
public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private List<Path> foundFiles = new ArrayList<Path>();
    private List<String> foundLines = new ArrayList<String>();
    private List<Record> records = new ArrayList<Record>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public List<String> getFoundLines() {
        return foundLines;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (file.toString().endsWith(".log")){
            foundFiles.add(file);
            foundLines.addAll(Files.readAllLines(file));
        }
        return super.visitFile(file, attrs);
    }
}
