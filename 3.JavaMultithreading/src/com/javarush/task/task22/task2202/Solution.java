package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        try {
            int s1 = string.indexOf(" ");
            int s2 = string.indexOf(" ",s1+1);
            int s3 = string.indexOf(" ",s2+1);
            int s4 = string.indexOf(" ",s3+1);
            int s5 = string.indexOf(" ",s4+1);

            if (s1!=-1 && s5!=-1){
                return string.substring(s1+1,s5);
            }else if(s1!=-1 && s4!=-1){
                return string.substring(s1+1);
            }else {
                throw new TooShortStringException();
            }
        }catch (Exception e){
                throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends RuntimeException {

    }
}
