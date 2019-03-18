package com.javarush.task.task18.task1828;

/* 
Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD

Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id

Значения параметров:
где id - 8 символов
productName - название товара, 30 символов
price - цена, 8 символов
quantity - количество, 4 символа
-u - обновляет данные товара с заданным id
-d - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19847   Шорты пляжные синие           159.00  12
198479  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234


Требования:
1. Программа должна считать имя файла для операций CrUD с консоли.
2. При запуске программы без параметров список товаров должен остаться неизменным.
3. При запуске программы с параметрами "-u id productName price quantity" должна обновится информация о товаре в файле.
4. При запуске программы с параметрами "-d id" строка товара с заданным id должна быть удалена из файла.
5. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Solution {
    public static void main(String[] args) throws IOException {
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String, String>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        File file = new File(bufferedReader.readLine());
        bufferedReader.close();
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        boolean hasChanges=false;
        String r;

        while ((r=bufferedReader.readLine())!=null){
            linkedHashMap.put(r.substring(0,8),r.substring(8,r.length()));
        }
        bufferedReader.close();

        if (args.length>1 && args[0].equals("-d") && !args[1].trim().equals("")){
            hasChanges=true;
            linkedHashMap.remove(formatString(args[1],8));
        }
        else if (args.length>3 && args[0].equals("-u") && !args[1].trim().equals("")){
            hasChanges=true;
            linkedHashMap.replace(formatString(args[1],8), formatString(args[2],30) + formatString(args[3],8)+formatString(args[4],4));
        }
        if (hasChanges){
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (Map.Entry<String,String> entry:linkedHashMap.entrySet()){
                bufferedWriter.write(entry.getKey()+entry.getValue());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }

    }
    private static String formatString(String s, int i){
        while (s.toCharArray().length<i){
            s=s+" ";
        }
        s=s.substring(0,i);
        return s;
    }
}
