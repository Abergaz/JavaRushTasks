package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* 
Генератор паролей
Реализуй логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов.
2) только цифры и латинские буквы разного регистра.
3) обязательно должны присутствовать цифры, и буквы разного регистра.
Все сгенерированные пароли должны быть уникальные.

Пример правильного пароля:
wMh7smNu


Требования:
1. Класс Solution должен содержать метод getPassword(), который возвращает ByteArrayOutputStream со сгенерированным паролем.
2. Длина пароля должна составлять 8 символов.
3. Пароль должен содержать хотя бы одну цифру.
4. Пароль должен содержать хотя бы одну латинскую букву нижнего регистра.
5. Пароль должен содержать хотя бы одну латинскую букву верхнего регистра.
6. Пароль не должен содержать других символов, кроме цифр и латинских букв разного регистра.
7. Сгенерированные пароли должны быть уникальными.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String s = "0123456789abcdefgigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String d = "0123456789";
        String lCase = "abcdefgigklmnopqrstuvwxyz";
        String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder password = new StringBuilder("");
        boolean isCorrect = false;
        while (!isCorrect) {
            if (password.toString().length() == 8) {
                isCorrect = true;
                boolean isCorrectD = hasSymbol(password, d);
                boolean isCorrectL = hasSymbol(password, lCase);
                boolean isCorrectU = hasSymbol(password, uCase);
                isCorrect=isCorrectD && isCorrectL && isCorrectU;
                if (!isCorrect){
                    password.replace(0,password.length(),"");
                }
            } else {
                int i = (int) (Math.random() * s.length());
                password.append(s.substring(i, i + 1));
                //System.out.println(password.toString());
            }
        }
        byteArrayOutputStream.write(password.toString().getBytes());
        return byteArrayOutputStream;
    }

    private static boolean hasSymbol(StringBuilder password, String s) {
        boolean ret=false;
        for (int i=0; i<8; i++){
            if (s.indexOf(password.charAt(i))>0){
                ret=true;
                break;
            }
        }
        return ret;
    }
}