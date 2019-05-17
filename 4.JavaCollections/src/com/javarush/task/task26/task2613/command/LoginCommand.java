package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public class LoginCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String[] logPass = {"", ""};
        while (!logPass[0].equals("123456789012") || !logPass[1].equals("1234")) {
            logPass = ConsoleHelper.getValidTwoDigits("Введите логин и пароль");
            if (!(logPass[0].matches("[0-9]{12}") && logPass[1].matches("[0-9]{4}")))
                ConsoleHelper.writeMessage("Введены не верный логин или пинкод");
        }
        ConsoleHelper.writeMessage("Добро пожаловать!");
    }
}
