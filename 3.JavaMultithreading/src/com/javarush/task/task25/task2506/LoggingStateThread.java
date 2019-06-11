package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread thread;
    State state;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        while (thread.getState() != State.TERMINATED) {
            if (state==null){
                state=thread.getState();
                System.out.println(state);
            }
            if (state!=thread.getState()){
                state=thread.getState();
                System.out.println(state);
            }
        }
    }
}
