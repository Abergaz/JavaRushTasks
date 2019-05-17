package com.javarush.task.task26.task2613.command;


import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {
    private String resourceFile = CashMachine.RESOURCE_PATH+"info_en";
    private ResourceBundle res = ResourceBundle.getBundle(resourceFile);

    @Override
    public void execute() {
        boolean hasMoney = false;
        ConsoleHelper.writeMessage(res.getString("before"));
        for (CurrencyManipulator currencyManipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (currencyManipulator.hasMoney()) hasMoney=true;
            if (currencyManipulator.getTotalAmount()>0){
                ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
            }
        }
        if (!hasMoney) ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
