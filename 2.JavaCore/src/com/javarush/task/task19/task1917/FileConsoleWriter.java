package com.javarush.task.task19.task1917;

/* 
Свой FileWriter
Класс FileConsoleWriter должен содержать приватное поле FileWriter fileWriter.
Класс FileConsoleWriter должен содержать все конструкторы, которые инициализируют fileWriter для записи.
Класс FileConsoleWriter должен содержать пять методов write и один метод close:

public void write(char[] cbuf, int off, int len) throws IOException
public void write(int c) throws IOException
public void write(String str) throws IOException
public void write(String str, int off, int len)
public void write(char[] cbuf) throws IOException
public void close() throws IOException
При записи данных в файл, должен дублировать эти данные на консоль.


Требования:
1. Класс FileConsoleWriter должен содержать приватное поле FileWriter fileWriter, которое не должно быть сразу проинициализировано.
2. Класс FileConsoleWriter должен иметь пять конструкторов которые инициализируют fileWriter для записи.
3. Класс FileConsoleWriter должен содержать метод write(char[] cbuf, int off, int len) throws IOException, в котором данные для записи должны записываться в fileWriter и дублироваться в консоль.
4. Класс FileConsoleWriter должен содержать метод write(int c) throws IOException, в котором данные для записи должны записываться в fileWriter и дублироваться в консоль.
5. Класс FileConsoleWriter должен содержать метод write(String str) throws IOException, в котором данные для записи должны записываться в fileWriter и дублироваться в консоль.
6. Класс FileConsoleWriter должен содержать метод write(String str, int off, int len) throws IOException, в котором данные для записи должны записываться в fileWriter и дублироваться в консоль.
7. Класс FileConsoleWriter должен содержать метод write(char[] cbuf) throws IOException, в котором данные для записи должны записываться в fileWriter и дублироваться в консоль.
8. Класс FileConsoleWriter должен содержать метод close() throws IOException, в котором должен вызываться такой же метод поля fileWriter.
*/

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileConsoleWriter  {
    private FileWriter fileWriter;
    public static void main(String[] args) throws IOException{

    }
    public FileConsoleWriter(String name) throws IOException{
        this.fileWriter = new FileWriter(name);
    }
    public FileConsoleWriter(String name, boolean append) throws IOException{
        this.fileWriter = new FileWriter(name,append);
    }
    public FileConsoleWriter(File file) throws IOException{
        this.fileWriter = new FileWriter(file);
    }
    public FileConsoleWriter(File file, boolean append) throws IOException{
        this.fileWriter = new FileWriter(file,append);
    }
    public FileConsoleWriter(FileDescriptor fd) throws IOException{
        this.fileWriter = new FileWriter(fd);
    }

    public void write(char[] cbuf, int off, int len) throws IOException{
        this.fileWriter.write(cbuf,off,len);
        System.out.print(Arrays.copyOfRange(cbuf,off,off+len));
    }
    public void write(int c) throws IOException{
        this.fileWriter.write(c);
        System.out.print(c);
    }
    public void write(String str) throws IOException{
        this.fileWriter.write(str);
        System.out.print(str);
    }
    public void write(String str, int off, int len) throws IOException {
        this.fileWriter.write(str,off,len);
        System.out.print(str.substring(off,off+len));
    }
    public void write(char[] cbuf) throws IOException{
        this.fileWriter.write(cbuf);
        System.out.print(cbuf);
    }
    public void close() throws IOException{
        this.fileWriter.close();

    }

}
