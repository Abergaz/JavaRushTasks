package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    private String resourceFile = CashMachine.RESOURCE_PATH+"exit_en";
    private ResourceBundle res = ResourceBundle.getBundle(resourceFile);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String answer = null;
        answer = ConsoleHelper.readString();
        if (answer.toLowerCase().equals("y")) ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
