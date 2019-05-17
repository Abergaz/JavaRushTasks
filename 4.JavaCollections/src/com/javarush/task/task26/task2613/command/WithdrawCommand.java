package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private String resourceFile = CashMachine.RESOURCE_PATH+"withdraw_en";
    private ResourceBundle res = ResourceBundle.getBundle(resourceFile);

    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        String expectedAmount="";
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            expectedAmount=ConsoleHelper.readString();
            if (expectedAmount.matches("\\d+")){
                if (currencyManipulator.isAmountAvailable(Integer.parseInt(expectedAmount))) break;
                else System.out.println(res.getString("not.enough.money"));
            }else {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
        }
        try {

            Map<Integer, Integer> map = currencyManipulator.withdrawAmount(Integer.parseInt(expectedAmount));
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"),expectedAmount,currencyCode));
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        }
    }
}
