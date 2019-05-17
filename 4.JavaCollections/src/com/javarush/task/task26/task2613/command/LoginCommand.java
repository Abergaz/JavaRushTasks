package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private String creditCardsFile = CashMachine.RESOURCE_PATH+"verifiedCards";
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(creditCardsFile);
    private String resourceFile = CashMachine.RESOURCE_PATH+"login_en";
    private ResourceBundle res = ResourceBundle.getBundle(resourceFile);


    @Override
    public void execute() throws InterruptOperationException {
        String log = "";
        String pass = "";
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            log = ConsoleHelper.readString();
            pass = ConsoleHelper.readString();
            if (log.matches("\\d+") && pass.matches("\\d+")) {
                if (log.matches("[0-9]{12}") && pass.matches("[0-9]{4}")) {
                    if (validCreditCards.containsKey(log) && validCreditCards.getString(log).equals(pass)){
                        break;
                    }
                    else{
                        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), log));
                        ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    }
                }else{
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), log));
    }
}
