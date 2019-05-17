package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static String resourceFile = CashMachine.RESOURCE_PATH+"common_en";
    private static ResourceBundle res = ResourceBundle.getBundle(resourceFile);
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String readLine=null;
        try {
            readLine=bis.readLine();
            if (readLine.toLowerCase().equals("exit")) throw new InterruptOperationException();
            return readLine;
        } catch (IOException e) {
            return readLine;
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String code = null;
        System.out.println(res.getString("choose.currency.code"));
        while (true) {
            code = readString();
            if (code.length() == 3 && code.replaceAll("[a-zA-Zа-яА-Я]{3}", "").equals("")) {
                return code.toUpperCase();
            }
            System.out.println(res.getString("invalid.data"));
        }
    }

    public static String[] getValidTwoDigits(String message) throws InterruptOperationException {
        String s;
        String[] strings = {"", ""};
        writeMessage(message);
        while (strings[0].equals("") && strings[1].equals("")) {
            s = readString();
            if (s.matches("\\d+\\s\\d+")) {
                if (Long.parseLong(s.split(" ")[0]) > 0 && Long.parseLong(s.split(" ")[1]) > 0) {
                    strings[0] = s.split(" ")[0];
                    strings[1] = s.split(" ")[1];
                    break;
                }
            } else {
                System.out.println(res.getString("choose.denomination.and.count.format"));
            }
        }
        return strings;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        String oper = "";
        while (!oper.matches("[1-5]")) {
            oper = readString();
        }
        int i = Integer.parseInt(oper);
        if (i==1) throw new IllegalArgumentException();
        Operation operation = Operation.getAllowableOperationByOrdinal(i);
        return operation;
    }
    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }


}
