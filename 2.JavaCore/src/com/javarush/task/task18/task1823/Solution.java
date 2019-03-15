package com.javarush.task.task18.task1823;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit".
Передайте имя файла в нить ReadThread.
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки.


Требования:
1. Программа должна считывать имена файлов с консоли, пока не будет введено слово "exit".
2. Для каждого файла создай нить ReadThread и запусти ее.
3. После запуска каждая нить ReadThread должна создать свой поток для чтения из файла.
4. Затем нити должны найти максимально встречающийся байт в своем файле и добавить его в словарь resultMap.
5. Поток для чтения из файла в каждой нити должен быть закрыт.
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        try {
            while (!(fileName=bufferedReader.readLine()).equals("exit")){
                   ReadThread readThread = new ReadThread(fileName);
                   readThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class ReadThread extends Thread {
        public ReadThread(String fileName) {
            //implement constructor body
            super(fileName);
        }
        // implement file reading here - реализуйте чтение из файла тут
        @Override
        public void run() {
            File file = new File(getName());
            FileInputStream fileInputStream;
            InputStreamReader inputStreamReader;
            BufferedReader bufferedReader1;
            TreeMap<Integer,Integer> treeMap = new TreeMap<Integer, Integer>();
            int r;
            try {
                fileInputStream = new FileInputStream(file);
                inputStreamReader = new InputStreamReader(fileInputStream);
                bufferedReader1 = new BufferedReader(inputStreamReader);
                while ((r=bufferedReader1.read())!=-1){
                    if (treeMap.containsKey(r)){
                        treeMap.replace(r,treeMap.get(r)+1);
                    }else {
                        treeMap.put(r,1);
                    }
                }
                bufferedReader1.close();
                inputStreamReader.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int maxValue=Integer.MIN_VALUE;
            int key=0;
            for (Map.Entry<Integer,Integer> entry: treeMap.entrySet()) {
                if (entry.getValue()>maxValue){
                    maxValue = entry.getValue();
                    key = entry.getKey();
                }
            }
            synchronized (Solution.resultMap) {
                Solution.resultMap.put(getName(), key);
            }
        }
    }
}
