package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат.

Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>

Первым параметром в метод main приходит тег. Например, "span".
Вывести на консоль все теги, которые соответствуют заданному тегу.
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле.
Количество пробелов, \n, \r не влияют на результат.
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нет.
Тег может содержать вложенные теги.

Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми


Требования:
1. Программа должна считывать имя файла с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое файла (используй FileReader).
4. Поток чтения из файла (FileReader) должен быть закрыт.
5. Программа должна выводить в консоль все теги, которые соответствуют тегу, заданному в параметре метода main.
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName=bufferedReader.readLine();
        bufferedReader.close();
        FileReader fileReader = new FileReader(fileName);
        bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        while (bufferedReader.ready()){
            stringBuilder.append(bufferedReader.readLine());
        }
        bufferedReader.close();
        fileReader.close();

        String s = stringBuilder.toString();
        ArrayList<String> arrayList = new ArrayList<String>();
        while (s.contains("</"+args[0]+">")){
            String line=findLine(s,args[0],arrayList);
            s=s.replace(line,"");
        }
        for (String el: arrayList) {
            System.out.println(el);
        }
    }
    private static String findLine(String s, String tag, ArrayList<String> arrayList){
        Boolean flag = true;
        int countOpen=0;
        int countClose=0;
        int spanStart = s.indexOf("<"+tag);
        int spanEnd;
        String returnS="";
        if (spanStart>0){
            countOpen++;
            String sub = s;
            int spanStartTmp=spanStart;
            int spanEndTmp=spanStart;
            boolean firstClose=true;
            while (countOpen!=countClose){
               if (countClose==0){
                   //Если пока не нашли не одного закрывающего, то ищем следующий открывающийся в строке от последнего открывающего
                   sub=sub.substring(spanStartTmp+6,sub.length());
                   spanStartTmp = sub.indexOf("<"+tag);
                   spanEndTmp = sub.indexOf("</"+tag+">")+6;
               }else{
                    //Если уже нашли хоть одит закрывающийся, то и следующие должны быть закрывающиеся и ищем их с строке от сдедующего закрывающегося
                   sub=sub.substring(spanEndTmp+7,sub.length());
                   spanEndTmp = sub.indexOf("</"+tag+">")+7;
               }
               if (spanEndTmp<spanStartTmp || spanStartTmp==-1) {
                   //слудующий тег <span идёт после </span
                   countClose++;
               }
               else{
                   //увеличиваем счетчик открыт
                   countOpen++;

               }
            }
        }
        spanEnd=0;
        int spanEndTmp=0;
        for(int i=0; i<countClose; i++){
            spanEndTmp=s.substring(spanEnd,s.length()).indexOf("</"+tag+">");
            spanEnd=spanEnd+spanEndTmp+7;
        }
        returnS=s.substring(spanStart,spanEnd);
        arrayList.add(returnS);

        if (countClose>1){
            s=returnS.substring(returnS.indexOf("<"+tag)+5);
            s=findLine(s,tag,arrayList);
        }


        return returnS;
    }

}
