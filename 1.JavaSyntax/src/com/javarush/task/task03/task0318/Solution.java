package com.javarush.task.task03.task0318;

/* 
План по захвату мира
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        try (InputStream inputStream = System.in) {
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            int age = Integer.parseInt(bufferedReader.readLine());
            String name = bufferedReader.readLine();

            System.out.println(name + " захватит мир через " + age + " лет. Му-ха-ха!");
        }

    }
}
