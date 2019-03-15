package com.javarush.task.task04.task0443;


/* 
Как назвали, так назвали
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name;
        int d,m,y;
        name = bufferedReader.readLine();
        y = Integer.parseInt(bufferedReader.readLine());
        m = Integer.parseInt(bufferedReader.readLine());
        d = Integer.parseInt(bufferedReader.readLine());

        System.out.println("Меня зовут " + name +".");
        System.out.println("Я родился " + d +"." + m + "." + y);
    }
}
