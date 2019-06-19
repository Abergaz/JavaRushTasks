package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {
    public static void main(String[] args) {
        new BotClient().run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = null;
            if (!message.contains(": ")) return;
            String userName = message.split(": ")[0];
            String mess = message.split(": ")[1];

            if ("дата".equalsIgnoreCase(mess)) {
                simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
            } else if ("день".equalsIgnoreCase(mess)) {
                simpleDateFormat = new SimpleDateFormat("d");
            } else if ("месяц".equalsIgnoreCase(mess)) {
                simpleDateFormat = new SimpleDateFormat("MMMM");
            } else if ("год".equalsIgnoreCase(mess)) {
                simpleDateFormat = new SimpleDateFormat("YYYY");
            } else if ("время".equalsIgnoreCase(mess)) {
                simpleDateFormat = new SimpleDateFormat("H:mm:ss");
            } else if ("час".equalsIgnoreCase(mess)) {
                simpleDateFormat = new SimpleDateFormat("H");
            } else if ("минуты".equalsIgnoreCase(mess)) {
                simpleDateFormat = new SimpleDateFormat("m");
            } else if ("секунды".equalsIgnoreCase(mess)) {
                simpleDateFormat = new SimpleDateFormat("s");
            }

            if (simpleDateFormat != null) {
                sendTextMessage(String.format("Информация для %s: %s",userName,simpleDateFormat.format(Calendar.getInstance().getTime())));
            }
        }
    }
}
