package com.javarush.task.task26.task2613.command;
import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private String resourceFile = CashMachine.RESOURCE_PATH+"deposit_en";
    private ResourceBundle res = ResourceBundle.getBundle(resourceFile);
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = null;
        ConsoleHelper.writeMessage(res.getString("before"));
        currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        String[] strings = ConsoleHelper.getValidTwoDigits("Введите номинал и количество купюр");
        currencyManipulator.addAmount(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), currencyManipulator.getTotalAmount(),currencyCode));
    }
}
