package com.javarush.task.task31.task3101;



import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/*
Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя (полный путь) существующего файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
2.2. Переименовать resultFileAbsolutePath в 'allFilesContent.txt' (используй метод FileUtils.renameFile, и, если понадобится, FileUtils.isExist).
2.3. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. После каждого тела файла записать "\n".
Все файлы имеют расширение txt.
В качестве разделителя пути используй "/".


Требования:
1. Файл, который приходит вторым параметром в main, должен быть переименован в allFilesContent.txt.
2. Нужно создать поток для записи в переименованный файл.
3. Содержимое всех файлов, размер которых не превышает 50 байт, должно быть записано в файл allFilesContent.txt в отсортированном по имени файла порядке.
4. Поток для записи в файл нужно закрыть.
5. Не используй статические переменные.
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        TreeMap<String,File> treeMap = new TreeMap<String, File>();
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        processFile(path, treeMap);
        File allFilesContent = new File(resultFileAbsolutePath.getParent()+"\\"+"allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        FileWriter fileWriter = new FileWriter(allFilesContent);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Map.Entry<String,File> entry: treeMap.entrySet()){
            File f = entry.getValue();
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()){
                bufferedWriter.write(bufferedReader.readLine());
            }
            bufferedWriter.newLine();
            bufferedReader.close();
            fileReader.close();
        }
        bufferedWriter.close();
        fileWriter.close();

    }
    private static void processFile(File fileIn, TreeMap<String,File> treeMap){
        for (File file : fileIn.listFiles()){
            if (file.isDirectory()){
                processFile(file, treeMap);
            }else{
                if (file.length()<=50){
                    treeMap.put(file.getName(), file);
                }
            }
        }
    }
}
