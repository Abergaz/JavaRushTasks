package com.javarush.task.task18.task1827;

/* 
Прайсы
CrUD для таблицы внутри файла.
Считать с консоли имя файла для операций CrUD.

Программа запускается со следующим набором параметров:
-c productName price quantity

Значения параметров:
где id - 8 символов.
productName - название товара, 30 символов.
price - цена, 8 символов.
quantity - количество, 4 символа.
-c - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле.

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity

Данные дополнены пробелами до их длины.

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234


Требования:
1. Программа должна считать имя файла для операций CrUD с консоли.
2. В классе Solution не должны быть использованы статические переменные.
3. При запуске программы без параметров список товаров должен остаться неизменным.
4. При запуске программы с параметрами "-c productName price quantity" в конец файла должна добавится новая строка с товаром.
5. Товар должен иметь следующий id, после максимального, найденного в файле.
6. Форматирование новой строки товара должно четко совпадать с указанным в задании.
7. Созданные для файлов потоки должны быть закрыты.
*/


import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        File file = new File(bufferedReader.readLine());
        bufferedReader.close();
        if(args.length == 4 && args[0].equals("-c")){
            StringBuilder stringBuilder = new StringBuilder();
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            int max = Integer.MIN_VALUE;
            String line;
            while ((line=bufferedReader.readLine())!=null){
                String sId=line.substring(0,8);
                sId = sId.trim();
                int id=Integer.parseInt(sId);
                max=(max<id) ? id : max;
            }
            bufferedReader.close();
            fileReader.close();

            max++;
            line=String.valueOf(max);

            stringBuilder.append(formatString(line,8));
            stringBuilder.append(formatString(args[1],30));
            stringBuilder.append(formatString(args[2],8));
            stringBuilder.append(formatString(args[3],4));

            FileWriter fileWriter = new FileWriter(file,true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(System.lineSeparator()+stringBuilder.toString());
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
